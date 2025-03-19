package com.jumbochips.chillguy.security.oauth;

import com.jumbochips.chillguy.user.entity.Provider;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(Provider provider, Map<String, Object> attributes) {
        if (provider == Provider.GOOGLE) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (provider == Provider.SPOTIFY) {
            return new SpotifyOAuth2UserInfo(attributes);
        } else {
            throw new IllegalArgumentException("Unsupported OAuth2 provider: " + provider.toString());
        }
    }
}
