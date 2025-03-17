package com.jumbochips.chillguy.playlist.service;

import com.jumbochips.chillguy.music.dto.MusicResponseDto;

import java.util.List;

public interface YouTubePlaylistService {
    String createPlaylist(String accessToken, String title, String description);
    String searchVideoId(String accessToken, String songTitle, String artist);
    void addToPlaylist(String accessToken, String playlistId, String videoId);
    void addRecommendMusicToPlaylist(String accessToken, String playlistId, List<MusicResponseDto> recommendedSongs);
}
