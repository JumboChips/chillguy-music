package com.jumbochips.chillguy.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbochips.chillguy.security.entity.CustomOAuth2User;
import com.jumbochips.chillguy.security.jwt.JwtTokenProvider;
import com.jumbochips.chillguy.security.oauth.OAuth2UserInfo;
import com.jumbochips.chillguy.security.oauth.OAuth2UserInfoFactory;
import com.jumbochips.chillguy.user.entity.Provider;
import com.jumbochips.chillguy.user.entity.User;
import com.jumbochips.chillguy.user.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;

    @Value("${frontend.url}")
    private String frontendUrl;

    // JWT를 HttpOnly Cookie로 설정하는 메서드
    private void setCookie(HttpServletResponse response, String name, String value, int maxAge, boolean secure) {
        String cookieString = String.format(
                "%s=%s; Max-Age=%d; Path=/; Domain=chillguy-music.com; HttpOnly; %s; SameSite=None",
                name,
                value,
                maxAge,
                secure ? "Secure" : ""
        );
        response.addHeader("Set-Cookie", cookieString);
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email;
        Provider provider = Provider.GOOGLE; // 현재는 Google만 지원
        OAuth2UserInfo oAuth2UserInfo;

        // DefaultOidcUser 또는 CustomOAuth2User에서 사용자 정보 가져오기
        if (oAuth2User instanceof DefaultOidcUser oidcUser) {
            email = oidcUser.getAttribute("email");
            oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider, oidcUser.getAttributes());
        } else if (oAuth2User instanceof CustomOAuth2User customUser) {
            email = customUser.getEmail();
            oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider, customUser.getAttributes());
        } else {
            throw new RuntimeException("지원되지 않는 OAuth2User 타입: " + oAuth2User.getClass().getName());
        }

        // DB에서 사용자 조회 (없으면 자동 회원 가입)
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            user = User.fromOAuth2User(oAuth2UserInfo, provider);
            userRepository.save(user);
        }

        // JWT 생성
        String accessToken = jwtTokenProvider.generateToken(user.getEmail(), true);
        String refreshToken = jwtTokenProvider.generateToken(user.getEmail(), false);
        user.updateRefreshToken(refreshToken);
        userRepository.save(user);

        // OAuth2 accessToken 가져오기
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient("google", oAuth2User.getName());
        if (authorizedClient == null) {
            throw new RuntimeException("Google OAuth2 인증 정보를 찾을 수 없습니다. 다시 로그인 해주세요.");
        }
        OAuth2AccessToken googleAccessToken = authorizedClient.getAccessToken();

        // 쿠키 저장
        boolean isSecure = !frontendUrl.contains("localhost"); // 로컬에서는 false, 운영에서는 true
        setCookie(response, "accessToken", accessToken, 900, true); // 15분
        setCookie(response, "refreshToken", refreshToken, 1209600, true); // 14일
        setCookie(response, "googleAccessToken", googleAccessToken.getTokenValue(), (int) googleAccessToken.getExpiresAt().getEpochSecond(), true); // googleAccessToken 저장

        // Authorization 헤더 추가 (fetchUser에서 헤더를 통해도 받을 수 있도록)
        response.setHeader("Authorization", "Bearer " + accessToken);

        // 프론트엔드로 리디렉트 (Nuxt 로그인 성공 페이지)
        response.sendRedirect(frontendUrl + "/");
    }
}
