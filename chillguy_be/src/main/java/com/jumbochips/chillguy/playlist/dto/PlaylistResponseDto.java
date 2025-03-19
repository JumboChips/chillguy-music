package com.jumbochips.chillguy.playlist.dto;

import com.jumbochips.chillguy.music.dto.MusicResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistResponseDto {
    private Long id;
    private String name;
    private List<MusicResponseDto> songs;
}
