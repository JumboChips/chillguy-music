package com.jumbochips.chillguy.music.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music")
@Tag(name = "음악", description = "사용자의 옵션에 따라 음악 추천")
public class MusicController {

    @Operation(summary = "빠른 음악 추천", description = "사용자의 기분/날씨/활동을 기반으로 빠르게 음악 추천")
    @PostMapping("/recommend/quick")
    public ResponseEntity<String> quickRecommend() {
        return ResponseEntity.ok("빠른 음악 추천 리스트 반환");
    }

    @Operation(summary = "사용자의 프롬프트 음악 추천", description = "사용자의 프롬프트를 기반으로 음악 추천")
    @PostMapping("/recommend/story")
    public ResponseEntity<String> storyRecommend() {
        return ResponseEntity.ok("음악 추천 리스트 반환");
    }


}
