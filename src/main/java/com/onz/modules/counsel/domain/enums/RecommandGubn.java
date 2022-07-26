package com.onz.modules.counsel.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecommandGubn {
    R("Recommand", "추천"),
    N("Notification", "신고")
    ;

    String code;
    String name;
}
