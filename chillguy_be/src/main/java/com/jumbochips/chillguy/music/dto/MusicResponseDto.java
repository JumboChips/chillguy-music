package com.jumbochips.chillguy.music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicResponseDto {

    private String title;
    private String artist;
    private String album;
    private String genre;
    private String albumCoverUrl;
    private String sourceUrl;
    private String reason;
}
