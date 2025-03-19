package com.jumbochips.chillguy.playlist.controller;

import com.jumbochips.chillguy.music.dto.MusicResponseDto;
import com.jumbochips.chillguy.playlist.dto.PlaylistCreateRequestDto;
import com.jumbochips.chillguy.playlist.service.YouTubePlaylistService;
import com.jumbochips.chillguy.user.principal.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
@Tag(name = "플레이리스트 관리", description = "사용자의 플레이리스트 생성, 수정, 삭제, 음악 추가/삭제 기능")
public class PlaylistController {

    private final YouTubePlaylistService youTubePlaylistService;

    @Operation(summary = "새 플레이리스트 생성", description = "사용자가 새로운 플레이리스트를 생성합니다.")
    @PostMapping
    public ResponseEntity<Map<String, Object>> createPlaylist(HttpServletRequest request,
                                                              @RequestBody PlaylistCreateRequestDto requestDto) {
        try {
            Map<String, Object> result = youTubePlaylistService.createPlaylist(request, requestDto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "플레이리스트 생성 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "플레이리스트 이름 수정", description = "특정 플레이리스트의 이름을 수정합니다.")
    @PutMapping("/{playlistId}")
    public ResponseEntity<String> updatePlaylist(@PathVariable Long playlistId) {
        return ResponseEntity.ok("플레이리스트 수정 완료");
    }

    @Operation(summary = "플레이리스트 삭제", description = "특정 플레이리스트를 삭제합니다.")
    @DeleteMapping("/{playlistId}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long playlistId) {
        return ResponseEntity.ok("플레이리스트 삭제 완료");
    }

    @Operation(summary = "음악 추가", description = "특정 플레이리스트에 음악을 추가합니다.")
    @PostMapping("/{playlistId}/add")
    public ResponseEntity<String> addRecommendMusicToPlaylist(HttpServletRequest request,
                                                              @PathVariable String playlistId,
                                                              @RequestBody List<MusicResponseDto> recommendedSongs) {
        youTubePlaylistService.addRecommendMusicToPlaylist(request, playlistId, recommendedSongs);
        return ResponseEntity.ok("추천 음악이 YouTube 플레이리스트에 추가되었습니다.");
    }

    @Operation(summary = "음악 삭제", description = "특정 플레이리스트에서 음악을 삭제합니다.")
    @DeleteMapping("/{playlistId}/remove/{musicId}")
    public ResponseEntity<String> removeMusic(@PathVariable Long playlistId, @PathVariable Long musicId) {
        return ResponseEntity.ok("음악 삭제 완료");
    }
}
