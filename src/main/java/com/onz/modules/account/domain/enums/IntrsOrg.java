package com.onz.modules.account.domain.enums;

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
public enum IntrsOrg {
    all("TT","전체"),
    kindergarten("CT", "유치원"),
    daycarecenter("PT", "어린이집"),
    ;
    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(IntrsOrg::getCode, IntrsOrg::name)));

    private String code;
    private String desc;

    public static IntrsOrg of(final String code){
        return IntrsOrg.valueOf(CODE_MAP.get(code));
    }
}
