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
                + "사용자 요구사항: \\n\\n"
                + userInput.getUserInput() + "\\n\\n"
                + "사용자가 입력한 요청에 완벽히 맞는 노래를 추천해야 해. 무엇이 됐든 사용자의 요구사항이 우선이야. "
                + "반드시 사용자의 요구사항(가사 내용, 분위기, 장르, 활동, 감정 등)을 충실히 따르고, 이에 기반하여 곡을 추천해줘.\\n"
                + "- 추천하는 곡은 반드시 요청된 조건을 만족해야 해.\\n"
                + "- 같은 아티스트나 동일한 앨범에서 여러 곡을 추천하지 마.(사용자의 요청이 아니라면) \\n"
                + "- 다양한 장르(Pop, Rock, Jazz, EDM, K-Pop, Hip-hop, Classical 등)와 다양한 시대(최신곡과 클래식 명곡 포함)의 곡을 적절히 섞어.\\n"
                + "- 특정 감정이나 활동(예: 운동, 독서, 드라이브)에 맞는 곡의 분위기와 가사를 고려해야 해.\\n"
                + "- 추천하는 각 곡이 왜 사용자의 요청과 일치하는지 한 문장으로 설명해야 해.\\n"
                + "- 반드시 JSON 형식으로만 10곡을 반환하고, 코드 블록(````json`)을 사용하지 마. 알겠습니다라는 답변도 말고 아래 예제 형식으로 바로 답변해.\\n\\n"
                + "예제 형식:\\n"
                + "[\\n"
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
                + "이 조건을 바탕으로 완벽하게 어울리는 음악을 10곡 추천해줘.\\n"
                + "- 추천 곡은 사용자의 기분, 활동, 날씨와 밀접하게 연관된 곡이어야 해.\\n"
                + "- 같은 아티스트나 같은 앨범의 곡을 여러 개 추천하지 마.\\n"
                + "- 다양한 장르(Pop, Rock, Jazz, EDM, K-Pop, Hip-hop, Classical 등)를 포함해야 해.\\n"
                + "- 최신 음악과 클래식 명곡을 적절히 조합해야 해.\\n"
                + "- 노래가 왜 이 조건에 맞는지 이유를 구체적으로 설명해줘.\\n"
                + "- 반드시 순수 JSON 형식으로만 응답하고, 코드 블록(````json`)을 포함하지 마. 알겠습니다라는 답변도 말고 아래 예제 형식으로 바로 답변해.\\n\\n"
                + "예제 형식:\\n"
                + "[\\n"
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
