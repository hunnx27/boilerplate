package com.onz.modules.counsel.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QnaItem {
    Q1("01", "고민상담"),
    Q2("02", "우리반아이"),
    Q3("03", "휴가/휴직"),
    Q4("04", "호봉/수당"),
    Q5("05", "승급/임용"),
    Q6("06", "동료/보조/대체"),
    Q7("07", "기관문의")
    ;

    String code;
    String name;
}
