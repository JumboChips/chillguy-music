package com.jumbochips.chillguy.external.service;

import com.jumbochips.chillguy.music.dto.MusicResponseDto;
import com.jumbochips.chillguy.music.dto.QuickOptionRequestDto;
import com.jumbochips.chillguy.music.dto.TextRecommendationRequestDto;

import java.util.List;

public interface GeminiMusicService {
    List<MusicResponseDto> createRecommendedMusic(TextRecommendationRequestDto userInput);
    List<MusicResponseDto> createQuickRecommendedMusic(QuickOptionRequestDto quickOption);
}
