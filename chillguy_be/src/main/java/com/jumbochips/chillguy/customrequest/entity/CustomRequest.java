package com.jumbochips.chillguy.customrequest.entity;

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
public class CustomRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String userInput;  // 사용자가 입력한 텍스트 요청
}
