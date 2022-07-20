package com.onz.modules.account.domain.embed;

import com.onz.common.enums.InterestOrg;
import com.onz.common.enums.InterestOrgConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Myinfo {
//    @Enumerated(EnumType.STRING)
    @Convert(converter = InterestOrgConverter.class)
    private InterestOrg interestOrg; // 관심 기관

    private String interestZone; // 관심지역

    private String birthYYYY; // 생년

    private String majorSchool; // 출신 학교

    private String majorDepartment; // 학과

    public Myinfo() {
    }

    public Myinfo(InterestOrg interestOrg, String interestZone, String birthYYYY, String majorSchool, String majorDepartment) {
        this.interestOrg = interestOrg;
        this.interestZone = interestZone;
        this.birthYYYY = birthYYYY;
        this.majorSchool = majorSchool;
        this.majorDepartment = majorDepartment;
    }
}
