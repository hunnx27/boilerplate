package com.onz.modules.account.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Gubn {
    TEACHER("A"),
    PARENT("I")
    ;
    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(Gubn::getCode, Gubn::name)));

    private String code;

    public static Gubn of(final String code){
        return Gubn.valueOf(CODE_MAP.get(code));
    }
}
