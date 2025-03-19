package com.jumbochips.chillguy.security.oauth;

import java.util.Map;

public class SpotifyOAuth2UserInfo extends OAuth2UserInfo {

    public SpotifyOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        return attributes.containsKey("email") ? attributes.get("email").toString() : null;
    }

    @Override
    public String getName() {
        return attributes.get("display_name").toString();
    }

    @Override
    public String getProfileImageUrl() {
        if (attributes.containsKey("images") && !((Iterable<?>) attributes.get("images")).iterator().hasNext()) {
            return ((Map<?, ?>) ((Iterable<?>) attributes.get("images")).iterator().next()).get("url").toString();
        }
        return null;
    }
}
