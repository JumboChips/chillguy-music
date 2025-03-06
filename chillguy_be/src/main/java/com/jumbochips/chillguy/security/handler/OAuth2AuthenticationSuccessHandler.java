package com.jumbochips.chillguy.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbochips.chillguy.security.entity.CustomOAuth2User;
import com.jumbochips.chillguy.security.jwt.JwtTokenProvider;
import com.jumbochips.chillguy.user.entity.User;
import com.jumbochips.chillguy.user.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Value("${frontend.url}")
    private String frontendUrl;

    // JWT를 HttpOnly Cookie로 설정하는 메서드
    private void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // HTTPS 환경에서 true 설정
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
        User user = userRepository.findByEmail(oauthUser.getEmail()).orElseThrow();

        String accessToken = jwtTokenProvider.generateToken(user.getEmail(), true);
        String refreshToken = jwtTokenProvider.generateToken(user.getEmail(), false);

        // Refresh Token을 DB 또는 Redis에 저장 가능
        user.updateRefreshToken(refreshToken);
        userRepository.save(user);

        // JWT를 HttpOnly Cookie에 저장
        setCookie(response, "accessToken", accessToken, 900); // 15분
        setCookie(response, "refreshToken", refreshToken, 1209600); // 14일

        // 프론트엔드로 리디렉트 (Nuxt 로그인 성공 페이지)
        response.sendRedirect(frontendUrl + "/");
    }
}
