package com.jumbochips.chillguy.security.controller;

import com.jumbochips.chillguy.common.exception.InvalidJwtException;
import com.jumbochips.chillguy.security.jwt.JwtTokenProvider;
import com.jumbochips.chillguy.user.dto.UserDto;
import com.jumbochips.chillguy.user.entity.User;
import com.jumbochips.chillguy.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "OAuth 인증", description = "OAuth 로그인 및 인증 관련 API")
@AllArgsConstructor
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Operation(summary = "Google OAuth 로그인", description = "Google 계정을 이용한 로그인")
    @GetMapping("/login/google")
    public ResponseEntity<UserDto> loginWithGoogle(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserDto userDto = new UserDto(user.getEmail(), user.getName(), user.getProfileImageUrl());

        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Spotify OAuth 로그인", description = "Spotify 계정을 이용한 로그인")
    @GetMapping("/login/spotify")
    public ResponseEntity<String> loginWithSpotify() {
        return ResponseEntity.ok("Spotify OAuth 로그인 URL 반환");
    }

    @Operation(summary = "JWT 토큰 갱신", description = "만료된 JWT 토큰을 갱신합니다.")
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(
            @RequestHeader("Authorization") String refreshToken) {
        if(refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }

        if(!jwtTokenProvider.validateToken(refreshToken)) {
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

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "로그아웃", description = "로그인된 사용자를 로그아웃합니다.")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie accessTokenCookie = new Cookie("accessToken", null);
        accessTokenCookie.setMaxAge(0);
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setMaxAge(0);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok("Logged out");
    }
}
