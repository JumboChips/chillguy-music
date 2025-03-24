package com.jumbochips.chillguy.security.controller;

import com.jumbochips.chillguy.security.jwt.JwtTokenProvider;
import com.jumbochips.chillguy.security.service.AuthService;
import com.jumbochips.chillguy.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
@Tag(name = "OAuth 인증", description = "OAuth 로그인 및 인증 관련 API")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Google OAuth 로그인", description = "Google 계정을 이용한 로그인")
    @GetMapping("/login/google")
    public ResponseEntity<Map<String, String>> loginWithGoogle(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return ResponseEntity.ok(authService.loginWithGoogle(oAuth2User));
    }

    // 추후 spotify 개발 시 사용
//    @Operation(summary = "Spotify OAuth 로그인", description = "Spotify 계정을 이용한 로그인")
//    @GetMapping("/login/spotify")
//    public ResponseEntity<String> loginWithSpotify() {
//        return ResponseEntity.ok("Spotify OAuth 로그인 URL 반환");
//    }

    @Operation(summary = "JWT 토큰 갱신", description = "만료된 JWT 토큰을 갱신합니다.")
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestHeader("Authorization") String refreshToken){
        return ResponseEntity.ok(authService.refreshAccessToken(refreshToken));
}

    @Operation(summary = "로그아웃", description = "로그인된 사용자를 로그아웃합니다.")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(value = "refreshToken", required = false) String refreshToken, HttpServletRequest request, HttpServletResponse response) {
        authService.logout(refreshToken, request, response);
        return ResponseEntity.ok("Logged out successfully");
    }
}
