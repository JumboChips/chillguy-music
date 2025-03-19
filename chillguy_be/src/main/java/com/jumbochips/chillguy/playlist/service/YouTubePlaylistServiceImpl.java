package com.jumbochips.chillguy.playlist.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbochips.chillguy.music.dto.MusicResponseDto;
import com.jumbochips.chillguy.playlist.dto.PlaylistCreateRequestDto;
import com.jumbochips.chillguy.security.util.OAuth2AccessTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YouTubePlaylistServiceImpl implements YouTubePlaylistService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final OAuth2AccessTokenUtil tokenUtil;

    public YouTubePlaylistServiceImpl(RestTemplateBuilder builder, ObjectMapper objectMapper, OAuth2AccessTokenUtil tokenUtil) {
        this.restTemplate = builder.build();
        this.objectMapper = objectMapper;
        this.tokenUtil = tokenUtil;
    }

    @Value("${youtube.url}")
    private String youtubeApiUrl;

    @Value("${youtube.api-key}")
    private String youtubeApiKey;

    @Override
    public Map<String, Object> createPlaylist(HttpServletRequest request, PlaylistCreateRequestDto requestDto) {
        String googleAccessToken = tokenUtil.getGoogleAccessToken(request);
        String url = youtubeApiUrl + "/playlists?part=snippet,status&key=" + youtubeApiKey;

        // 플레이리스트 생성 요청 데이터
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(googleAccessToken);

        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> snippet = new HashMap<>();
        snippet.put("title", requestDto.getTitle());
        snippet.put("description", requestDto.getDescription());
        requestBody.put("snippet", snippet);

        Map<String, String> status = new HashMap<>();
        status.put("privacyStatus", "public");
        requestBody.put("status", status);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                JsonNode root = objectMapper.readTree(response.getBody());
                String playlistId = root.path("id").asText(); // 생성된 Playlist ID

                // 생성된 플레이리스트에 노래 추가 후 결과 반환
                Map<String, Object> addMusicResult = addRecommendMusicToPlaylist(request, playlistId, requestDto.getSongs());

                // 최종 응답 구성
                Map<String, Object> result = new HashMap<>();
                result.put("playlistId", playlistId);
                result.put("addedSongs", addMusicResult.get("addedSongs"));
                result.put("failedSongs", addMusicResult.get("failedSongs"));
                return result;

            } catch (Exception e) {
                throw new RuntimeException("Playlist 생성 실패", e);
            }
        } else {
            throw new RuntimeException("YouTube API 호출 실패: " + response.getBody());
        }
    }

    @Override
    public Map<String, Object> addRecommendMusicToPlaylist(HttpServletRequest request, String playlistId, List<MusicResponseDto> songs) {
        List<String> addedSongs = new ArrayList<>();
        List<String> failedSongs = new ArrayList<>();


        for (MusicResponseDto song : songs) {
            try {
                // YouTube에서 videoId 검색
                String videoId = searchVideoId(request, song.getTitle(), song.getArtist());

                if (videoId != null) {
                    // 검색된 videoId를 플레이리스트에 추가
                    addToPlaylist(request, playlistId, videoId);
                    addedSongs.add(song.getTitle());  // 성공한 곡 추가
                } else {
                    failedSongs.add(song.getTitle()); // 검색 실패한 곡 추가
                }
            } catch (Exception e) {
                failedSongs.add(song.getTitle());  // 추가 실패한 곡 저장
                System.err.println("Failed to add song: " + song.getTitle() + " - " + e.getMessage());
            }
        }

        // ✅ 프론트엔드에 결과 반환
        Map<String, Object> response = new HashMap<>();
        response.put("addedSongs", addedSongs);
        response.put("failedSongs", failedSongs);
        return response;

    }

    @Override
    public String searchVideoId(HttpServletRequest request, String songTitle, String artist) {
        String googleAccessToken = tokenUtil.getGoogleAccessToken(request);
        String url = youtubeApiUrl + "/search?part=snippet&q=" + songTitle + " " + artist + "&type=video";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(googleAccessToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

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
    public void addToPlaylist(HttpServletRequest request, String playlistId, String videoId) {
        String googleAccessToken = tokenUtil.getGoogleAccessToken(request);
        String url = youtubeApiUrl + "/playlistItems?part=snippet";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(googleAccessToken);

        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> snippet = new HashMap<>();
        snippet.put("playlistId", playlistId);
        snippet.put("resourceId", Map.of("kind", "youtube#video", "videoId", videoId));

        requestBody.put("snippet", snippet);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }
}
