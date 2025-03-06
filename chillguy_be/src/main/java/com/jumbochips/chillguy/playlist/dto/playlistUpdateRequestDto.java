package com.jumbochips.chillguy.playlist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class playlistUpdateRequestDto {
    @NotBlank
    private String newName;
}
