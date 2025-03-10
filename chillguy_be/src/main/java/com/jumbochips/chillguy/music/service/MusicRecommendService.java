package com.jumbochips.chillguy.music.service;

import com.jumbochips.chillguy.music.dto.MusicResponseDto;
import com.jumbochips.chillguy.music.dto.QuickOptionRequestDto;
import com.jumbochips.chillguy.music.dto.TextRecommendationRequestDto;

import java.util.List;

public interface MusicRecommendService {
    List<MusicResponseDto> createQuickRecommend (QuickOptionRequestDto userInput);
    List<MusicResponseDto> createTextRecommend (TextRecommendationRequestDto quickOption);
}
