package com.jumbochips.chillguy.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "OAuth 인증", description = "OAuth 로그인 및 인증 관련 API")
public class AuthController {

    @Operation(summary = "Google OAuth 로그인", description = "Google 계정을 이용한 로그인")
    @GetMapping("/login/google")
    public ResponseEntity<String> loginWithGoogle() {
        return ResponseEntity.ok("Google OAuth 로그인 URL 반환");
    }

    @Operation(summary = "Spotify OAuth 로그인", description = "Spotify 계정을 이용한 로그인")
    @GetMapping("/login/spotify")
    public ResponseEntity<String> loginWithSpotify() {
        return ResponseEntity.ok("Spotify OAuth 로그인 URL 반환");
    }

    @Operation(summary = "JWT 토큰 갱신", description = "만료된 JWT 토큰을 갱신합니다.")
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken() {
        return ResponseEntity.ok("JWT 토큰 갱신됨");
    }
}
