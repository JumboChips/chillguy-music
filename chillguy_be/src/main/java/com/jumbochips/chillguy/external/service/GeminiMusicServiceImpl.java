package com.jumbochips.chillguy.external.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbochips.chillguy.music.dto.MusicResponseDto;
import com.jumbochips.chillguy.music.dto.QuickOptionRequestDto;
import com.jumbochips.chillguy.music.dto.TextRecommendationRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GeminiMusicServiceImpl implements GeminiMusicService {

    @Value("${gemini.api-key}")
    private String apiKey;

    @Value("${gemini.url}")
    private String url;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GeminiMusicServiceImpl(RestTemplateBuilder builder, ObjectMapper objectMapper) {
        this.restTemplate = builder.build();
        this.objectMapper = objectMapper;
    }

    @Override
    public List<MusicResponseDto> createRecommendedMusic(TextRecommendationRequestDto userInput) {
        // userInput을 프롬프트에 반영하여 AI에게 요청
        String requestBody = "{ \"contents\": [{ \"parts\": [{\"text\": \""
                + userInput.getUserInput() + "\\n\\n"
                + "아래 JSON 형식으로 10곡을 추천해줘. "
                + "반드시 순수 JSON만 반환하고, 코드 블록(````json`)을 포함하지 마:\n\n"
                + "[\\n  { \\\"title\\\": \\\"Shape of You\\\", \\\"artist\\\": \\\"Ed Sheeran\\\", \\\"album\\\": \\\"÷\\\", \\\"genre\\\": \\\"Pop\\\", \\\"reason\\\": \\\"추천이유.\\\" }\\n]\n"
                + "\"}]}] }";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url + apiKey,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            System.out.println("Gemini API 응답: " + response.getBody());


            if (response.getStatusCode() == HttpStatus.OK) {
                JsonNode root = objectMapper.readTree(response.getBody());
                JsonNode candidates = root.path("candidates");

                if (!candidates.isEmpty()) {
                    String jsonText = candidates.get(0)
                            .path("content")
                            .path("parts")
                            .get(0)
                            .path("text")
                            .asText();

                    // 코드블록 제거
                    jsonText = jsonText.replaceAll("^```json\\s*", "").replaceAll("\\s*```$", "");

                    return objectMapper.readValue(jsonText, new TypeReference<List<MusicResponseDto>>() {});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }


    @Override
    public List<MusicResponseDto> createQuickRecommendedMusic(QuickOptionRequestDto quickOption) {
        // quickOption 요청도 JSON 형식으로 응답하도록 프롬프트 포함
        String prompt = String.format("나는 %s한 기분이고, 날씨는 %s이며, %s 활동을 하고 있어. 이에 맞는 음악을 10곡 추천해줘.",
                quickOption.getMood(), quickOption.getWeather(), quickOption.getActivity());

        String requestBody = "{ \"contents\": [{ \"parts\": [{\"text\": \""
                + prompt + "\\n\\n"
                + "아래 JSON 형식으로 10곡을 추천해줘. "
                + "반드시 순수 JSON만 반환하고, 코드 블록(````json`)을 포함하지 마:\n\n"
                + "[\\n  { \\\"title\\\": \\\"Shape of You\\\", \\\"artist\\\": \\\"Ed Sheeran\\\", \\\"album\\\": \\\"÷\\\", \\\"genre\\\": \\\"Pop\\\", \\\"reason\\\": \\\"추천이유.\\\" }\\n]\n"
                + "\"}]}] }";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url + apiKey,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            System.out.println("Gemini API 응답: " + response.getBody());


            if (response.getStatusCode() == HttpStatus.OK) {
                JsonNode root = objectMapper.readTree(response.getBody());
                JsonNode candidates = root.path("candidates");

                if (!candidates.isEmpty()) {
                    String jsonText = candidates.get(0)
                            .path("content")
                            .path("parts")
                            .get(0)
                            .path("text")
                            .asText();

                    // 코드블록 제거
                    jsonText = jsonText.replaceAll("^```json\\s*", "").replaceAll("\\s*```$", "");

                    return objectMapper.readValue(jsonText, new TypeReference<List<MusicResponseDto>>() {});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

}
