package com.jumbochips.chillguy.security.service;

import com.jumbochips.chillguy.security.entity.CustomOAuth2User;
import com.jumbochips.chillguy.security.oauth.OAuth2UserInfo;
import com.jumbochips.chillguy.security.oauth.OAuth2UserInfoFactory;
import com.jumbochips.chillguy.user.entity.Provider;
import com.jumbochips.chillguy.user.entity.User;
import com.jumbochips.chillguy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // "google" 또는 "spotify"

        Provider provider = Provider.from(registrationId);

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider, oAuth2User.getAttributes());

        if (oAuth2UserInfo.getEmail() == null) {
            throw new OAuth2AuthenticationException("OAuth2 provider did not return email.");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());


        User user = userOptional.map(existingUser -> {
            existingUser.updateUserInfo(oAuth2UserInfo);
            return existingUser;
        }).orElseGet(()-> userRepository.save(User.fromOAuth2User(oAuth2UserInfo, provider)));

        return new CustomOAuth2User(user, oAuth2User.getAttributes());
    }
}
