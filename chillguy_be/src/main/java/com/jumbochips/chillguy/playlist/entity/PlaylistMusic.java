package com.jumbochips.chillguy.playlist.entity;

import com.jumbochips.chillguy.music.entity.Music;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "playlist_music")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistMusic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;
}
