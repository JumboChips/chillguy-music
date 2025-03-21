package com.jumbochips.chillguy.music.entity;

import com.jumbochips.chillguy.common.BaseTimeEntity;
import com.jumbochips.chillguy.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "custom_request")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomRequest extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String userInput;  // 사용자가 입력한 텍스트 요청
}
