package com.jumbochips.chillguy.music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuickOptionRequestDto {

    private String mood; // 사용자의 기분
    private String weather; // 현재 날씨
    private String activity; // 사용자의 활동
}
