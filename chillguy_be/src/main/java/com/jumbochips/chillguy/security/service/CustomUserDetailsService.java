package com.jumbochips.chillguy.security.service;

import com.jumbochips.chillguy.security.oauth.GoogleOAuth2UserInfo;
import com.jumbochips.chillguy.security.oauth.OAuth2UserInfo;
import com.jumbochips.chillguy.user.entity.User;
import com.jumbochips.chillguy.user.principal.UserPrincipal;
import com.jumbochips.chillguy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        OAuth2UserInfo userInfo = new GoogleOAuth2UserInfo(Map.of(
                "email", user.getEmail(),
                "name", user.getName(),
                "picture", user.getProfileImageUrl()
        ));

        return new UserPrincipal(userInfo, Collections.emptyList());
    }
}
