package com.jumbochips.chillguy.playlist.dto;

import com.jumbochips.chillguy.music.dto.MusicResponseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistCreateRequestDto {
    @NotBlank
    private String title;
    private String description;
    private List<MusicResponseDto> songs;
}
