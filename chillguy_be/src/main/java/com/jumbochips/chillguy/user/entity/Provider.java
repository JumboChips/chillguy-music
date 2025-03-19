package com.jumbochips.chillguy.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Provider {

    GOOGLE("google"),
    SPOTIFY("spotify");

    private final String registrationId;

    public static Provider from(String registrationId) {
        for (Provider provider : values()) {
            if (provider.registrationId.equalsIgnoreCase(registrationId)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("Unsupported provider: " + registrationId);
    }

}
