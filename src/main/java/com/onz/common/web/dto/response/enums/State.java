package com.onz.common.web.dto.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum State {
    W("Wait", "승인대기"),
    A("Accept", "승인"),
    R("Reject", "승인거절")
    ;

    String code;
    String name;
}
