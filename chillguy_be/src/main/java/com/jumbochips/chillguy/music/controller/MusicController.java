package com.jumbochips.chillguy.music.controller;

import com.jumbochips.chillguy.music.dto.MusicResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/music")
@Tag(name = "음악", description = "사용자의 옵션에 따라 음악 추천")
public class MusicController {

    @Operation(summary = "빠른 음악 추천", description = "사용자의 기분/날씨/활동을 기반으로 빠르게 음악 추천")
    @PostMapping("/recommend")
    public ResponseEntity<String> recommendMusic() {
        return ResponseEntity.ok("빠른 음악 추천 리스트 반환");
    }

    @Operation(summary = "텍스트 기반 음악 추천", description = "User Input text 기반으로 음악 추천")
    @PostMapping("/recommend/text")
    public ResponseEntity<String> storyRecommend() {
        return ResponseEntity.ok("음악 추천 리스트 반환");
    }


}
