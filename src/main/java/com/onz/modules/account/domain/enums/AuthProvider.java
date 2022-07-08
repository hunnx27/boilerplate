package com.onz.modules.account.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

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

    String code;

}
