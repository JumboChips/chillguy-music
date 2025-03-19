package com.jumbochips.chillguy.music.controller;

import com.jumbochips.chillguy.music.dto.MusicResponseDto;
import com.jumbochips.chillguy.music.dto.QuickOptionRequestDto;
import com.jumbochips.chillguy.music.dto.TextRecommendationRequestDto;
import com.jumbochips.chillguy.music.service.MusicRecommendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/music")
@Tag(name = "음악", description = "사용자의 옵션에 따라 음악 추천")
@RequiredArgsConstructor
public class MusicController {

    private final MusicRecommendService musicRecommendService;

    @Operation(summary = "빠른 음악 추천", description = "사용자의 기분/날씨/활동을 기반으로 빠르게 음악 추천")
    @PostMapping("/recommend")
    public ResponseEntity<List<MusicResponseDto>> recommendMusic(@RequestBody QuickOptionRequestDto request) {
        List<MusicResponseDto> response = musicRecommendService.createQuickRecommend(request);
        return ResponseEntity.ok(response);

    }

    @Operation(summary = "텍스트 기반 음악 추천", description = "User Input text 기반으로 음악 추천")
    @PostMapping("/recommend/text")
    public ResponseEntity<List<MusicResponseDto>> storyRecommend(@RequestBody TextRecommendationRequestDto request) {
        List<MusicResponseDto> response = musicRecommendService.createTextRecommend(request);
        return ResponseEntity.ok(response);
    }


}
