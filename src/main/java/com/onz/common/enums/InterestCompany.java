package com.onz.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 관심 기관
 */
@Getter
@AllArgsConstructor
public enum InterestCompany {
    all("TT","전체"),
    kindergarten("CT", "유치원"),
    daycarecenter("PT", "어린이집"),
    ;
    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(InterestCompany::getCode, InterestCompany::name)));

    private String code;
    private String desc;

    public static InterestCompany of(final String code){
        return InterestCompany.valueOf(CODE_MAP.get(code));
    }
}
