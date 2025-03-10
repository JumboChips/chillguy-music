package com.jumbochips.chillguy.music.service;

import com.jumbochips.chillguy.external.service.GeminiMusicService;
import com.jumbochips.chillguy.music.dto.MusicResponseDto;
import com.jumbochips.chillguy.music.dto.QuickOptionRequestDto;
import com.jumbochips.chillguy.music.dto.TextRecommendationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicRecommendServiceImpl implements MusicRecommendService {

    private final GeminiMusicService geminiMusicService;


    @Override
    public List<MusicResponseDto> createQuickRecommend(QuickOptionRequestDto quickOption) {
        return geminiMusicService.createQuickRecommendedMusic(quickOption);

    }

    @Override
    public List<MusicResponseDto> createTextRecommend(TextRecommendationRequestDto userInput) {
        return geminiMusicService.createRecommendedMusic(userInput);

    }
}
