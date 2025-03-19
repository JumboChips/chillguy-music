package com.jumbochips.chillguy.playlist.service;

import com.jumbochips.chillguy.music.dto.MusicResponseDto;
import com.jumbochips.chillguy.playlist.dto.PlaylistCreateRequestDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

public interface YouTubePlaylistService {
    Map<String, Object> createPlaylist(HttpServletRequest request, PlaylistCreateRequestDto requestDto);
    String searchVideoId(HttpServletRequest request, String songTitle, String artist);
    void addToPlaylist(HttpServletRequest request, String playlistId, String videoId);
    Map<String, Object> addRecommendMusicToPlaylist(HttpServletRequest request, String playlistId, List<MusicResponseDto> recommendedSongs);
}
