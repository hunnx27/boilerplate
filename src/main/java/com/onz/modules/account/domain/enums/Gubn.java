package com.onz.modules.account.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gubn {
    TEACHER("A"),
    PARENT("I")
    ;

    private String code;
}
