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
    ALL("TT", "all", "전체"),
    KINDERGARTEN("CT", "kindergarten", "유치원"),
    DAYCARECENTER("PT", "daycarecenter", "어린이집"),
    ;
    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(IntrsOrg::getCode, IntrsOrg::name)));

    private static final Map<String, String> NAME_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(IntrsOrg::getName, IntrsOrg::getCode)));

    private String code;
    private String name;
    private String desc;

    public static IntrsOrg of(final String code){
        return IntrsOrg.valueOf(CODE_MAP.get(code));
    }
    public static IntrsOrg ofName(final String name){
        return IntrsOrg.of(NAME_MAP.get(name));
    }
}
