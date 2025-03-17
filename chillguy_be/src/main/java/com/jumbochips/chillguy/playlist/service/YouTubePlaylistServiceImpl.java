package com.jumbochips.chillguy.playlist.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbochips.chillguy.music.dto.MusicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YouTubePlaylistServiceImpl implements YouTubePlaylistService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public YouTubePlaylistServiceImpl(RestTemplateBuilder builder, ObjectMapper objectMapper) {
        this.restTemplate = builder.build();
        this.objectMapper = objectMapper;
    }

    @Value("${youtube.url}")
    private String youtubeApiUrl;

    @Override
    public String createPlaylist(String accessToken, String title, String description) {
        String url = youtubeApiUrl + "/playlists?part=snippet,status";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> snippet = new HashMap<>();
        snippet.put("title", title);
        snippet.put("description", description);
        requestBody.put("snippet", snippet);

        Map<String, String> status = new HashMap<>();
        status.put("privacyStatus", "public");
        requestBody.put("status", status);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                JsonNode root = objectMapper.readTree(response.getBody());
                return root.path("id").asText(); // 생성된 Playlist ID 반환
            } catch (Exception e) {
                throw new RuntimeException("Playlist 생성 실패", e);
            }
        } else {
            throw new RuntimeException("YouTube API 호출 실패: " + response.getBody());
        }
    }

    @Override
    public String searchVideoId(String accessToken, String songTitle, String artist) {
        String url = youtubeApiUrl + "/search?part=snippet&q=" + songTitle + " " + artist + "&type=video";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                JsonNode root = objectMapper.readTree(response.getBody());
                return root.path("items").get(0).path("id").path("videoId").asText();
            } catch (Exception e) {
                throw new RuntimeException("YouTube 검색 실패", e);
            }
        }
        return null;
    }

    @Override
    public void addToPlaylist(String accessToken, String playlistId, String videoId) {
        String url = youtubeApiUrl + "/playlistItems?part=snippet";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> snippet = new HashMap<>();
        snippet.put("playlistId", playlistId);
        snippet.put("resourceId", Map.of("kind", "youtube#video", "videoId", videoId));

        requestBody.put("snippet", snippet);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        restTemplate.exchange(url, HttpMethod.POST, request, String.class);
    }

    @Override
    public void addRecommendMusicToPlaylist(String accessToken, String playlistId, List<MusicResponseDto> recommendedSongs) {
        for (MusicResponseDto song : recommendedSongs) {
            String videoId = searchVideoId(accessToken, song.getTitle(), song.getArtist());
            if (videoId != null) {
                addToPlaylist(accessToken, playlistId, videoId);
            }
        }
    }
}
