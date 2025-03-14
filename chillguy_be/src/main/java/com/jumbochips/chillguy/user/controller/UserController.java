package com.jumbochips.chillguy.user.controller;

import com.jumbochips.chillguy.user.dto.UserDto;
import com.jumbochips.chillguy.user.principal.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(new UserDto(
                userPrincipal.getEmail(),
                userPrincipal.getName(),
                userPrincipal.getProfileImageUrl()
        ));

    }
}
