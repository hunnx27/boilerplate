package com.onz.modules.account.domain.enums;

import java.util.Arrays;

public enum RestrictionProvider {
    LOCAL,
    FACEBOOK,
    GOOGLE,
    GITLAB,
    KAKAO,
    NAVER
    ;

    public static RestrictionProvider of(String id) {
        return Arrays.stream(RestrictionProvider.values())
            .filter(provider -> provider.name().equalsIgnoreCase(id))
            .findFirst()
            .orElse(null);
    }

}
