package com.jumbochips.chillguy.security.service;

import com.jumbochips.chillguy.common.exception.InvalidJwtException;
import com.jumbochips.chillguy.security.jwt.JwtTokenProvider;
import com.jumbochips.chillguy.user.entity.User;
import com.jumbochips.chillguy.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public Map<String, String> loginWithGoogle(OAuth2User oAuth2User) {
        User user = userRepository.findByEmail(oAuth2User.getAttribute("email"))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // OAuth2 액세스 토큰 가져오기
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                "google", oAuth2User.getName()
        );

        if (authorizedClient == null) {
            throw new RuntimeException("OAuth2 인증 정보를 찾을 수 없습니다.");
        }

        String accessToken = authorizedClient.getAccessToken().getTokenValue();

        Map<String, String> response = new HashMap<>();
        response.put("email", user.getEmail());
        response.put("name", user.getName());
        response.put("profileImage", user.getProfileImageUrl());
        response.put("youtubeAccessToken", accessToken);

        return response;
    }

    public Map<String, String> refreshAccessToken(String refreshToken) {
        if (refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }

        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new InvalidJwtException("Invalid Refresh Token", HttpStatus.UNAUTHORIZED);
        }

        String email = jwtTokenProvider.getEmailFromToken(refreshToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!refreshToken.equals(user.getRefreshToken())) {
            throw new InvalidJwtException("Refresh Token mismatch", HttpStatus.UNAUTHORIZED);
        }

        String newAccessToken = jwtTokenProvider.generateToken(user.getEmail(), true);

        Map<String, String> response = new HashMap<>();
        response.put("access_token", newAccessToken);

        return response;
    }


    public void logout(String refreshToken, HttpServletRequest request, HttpServletResponse response) {
        if (refreshToken != null) {
            String email = jwtTokenProvider.getEmailFromToken(refreshToken);
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            user.updateRefreshToken(null);
            userRepository.save(user);
        }

        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(false);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(0);
        response.addCookie(refreshTokenCookie);

        Cookie accessTokenCookie = new Cookie("accessToken", null);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(false);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(0);
        response.addCookie(accessTokenCookie);

        Cookie googleAccessTokenCookie = new Cookie("googleAccessToken", null);
        googleAccessTokenCookie.setHttpOnly(true);
        googleAccessTokenCookie.setSecure(false);
        googleAccessTokenCookie.setPath("/");
        googleAccessTokenCookie.setMaxAge(0);
        response.addCookie(googleAccessTokenCookie);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }



}
