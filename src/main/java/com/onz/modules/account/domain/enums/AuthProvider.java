package com.onz.modules.account.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum AuthProvider {
    local("L"),
    facebook("F"),
    google("G"),
    kakao("K"),
    naver("N"),
    apple("A")
    ;
    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(AuthProvider::getCode, AuthProvider::name)));

    private String code;

    public static AuthProvider of(final String code){
        return AuthProvider.valueOf(CODE_MAP.get(code));
    }

}
