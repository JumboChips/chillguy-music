package com.jumbochips.chillguy.security.entity;

import com.jumbochips.chillguy.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.List;
import java.util.Map;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {
    private final User user;

    public CustomOAuth2User(User user, Map<String, Object> attributes) {
        super(List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())), attributes, "email");
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getName();
    }

    public String getEmail() {
        return user.getEmail();
    }
}
