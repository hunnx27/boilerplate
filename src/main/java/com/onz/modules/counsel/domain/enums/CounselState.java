package com.onz.modules.counsel.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CounselState {
    R("Ready", "답변요청"),
    A("Adopted","답변완료")
    ;

    String code;
    String name;
}
//WAR