package com.jumbochips.chillguy.user.entity;

import com.jumbochips.chillguy.common.BaseTimeEntity;
import com.jumbochips.chillguy.security.oauth.OAuth2UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; // OAuth 제공자로부터 받은 이메일

    @Column(nullable = false)
    private String name;

    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Provider provider; // OAuth 제공자


    @Column(nullable = false)
    private String providerId; // OAuth 제공자로부터 받은 ID

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String refreshToken;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static User fromOAuth2User(OAuth2UserInfo oAuth2UserInfo, Provider provider) {
        return User.builder()
                .email(oAuth2UserInfo.getEmail())
                .provider(provider)
                .providerId(oAuth2UserInfo.getId())
                .name(oAuth2UserInfo.getName())
                .profileImageUrl(oAuth2UserInfo.getProfileImageUrl())
                .role(Role.USER)
                .build();
    }

    public void updateUserInfo(OAuth2UserInfo userInfo) {
        this.name = userInfo.getName();
        this.profileImageUrl = userInfo.getProfileImageUrl();
    }
}
