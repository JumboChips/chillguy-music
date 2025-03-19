package com.jumbochips.chillguy.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private String email;
    private String name;
    private String profileImage;
}
