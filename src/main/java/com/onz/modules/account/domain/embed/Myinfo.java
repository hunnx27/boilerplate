package com.onz.modules.account.domain.embed;

import com.onz.modules.account.domain.enums.GubnConverter;
import com.onz.modules.account.domain.enums.IntrsOrg;
import com.onz.modules.account.domain.enums.IntrsOrgConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Embeddable
public class Myinfo {
//    @Enumerated(EnumType.STRING)
    @Convert(converter = IntrsOrgConverter.class)
    private IntrsOrg intrsOrg; // 관심 기관

    private String IntrsZone="12345"; // 관심지역

    private String birthYYYY; // 생년

    private String majorSchool; // 출신 학교

    private String majorDepartment; // 학과

    public Myinfo() {
    }

    public Myinfo(IntrsOrg intrsOrg, String intrsZone, String birthYYYY, String majorSchool, String majorDepartment) {
        this.intrsOrg = intrsOrg;
        this.IntrsZone = intrsZone;
        this.birthYYYY = birthYYYY;
        this.majorSchool = majorSchool;
        this.majorDepartment = majorDepartment;
    }
}
