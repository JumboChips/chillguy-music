package com.jumbochips.chillguy.music.entity;

import com.jumbochips.chillguy.common.BaseTimeEntity;
import com.jumbochips.chillguy.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quick_option")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuickOptions extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String mood;        // 기분 (Happy, Sad, Chill 등)
    private String activity;    // 활동 (운동, 공부 등)
    private String weather;     // 날씨 (Sunny, Rainy 등)
}
