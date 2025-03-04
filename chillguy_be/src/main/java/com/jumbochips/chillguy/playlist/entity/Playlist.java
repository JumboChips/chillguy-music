package com.jumbochips.chillguy.playlist.entity;

import com.jumbochips.chillguy.common.BaseTimeEntity;
import com.jumbochips.chillguy.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "playlist")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Playlist extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private boolean isAiGenerated;

    private String recommendationReason;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    private List<PlaylistMusic> playlistMusicList;
}
