package com.onz.config.security.oauth2;

import java.util.Arrays;

public enum RestrictionProvider {
    KAKAO, GOOGLE, FACEBOOK;

    public static RestrictionProvider of(String id) {
        return Arrays.stream(RestrictionProvider.values())
            .filter(provider -> provider.name().equalsIgnoreCase(id))
            .findFirst()
            .orElse(null);
    }

}
