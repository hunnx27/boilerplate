package com.onz.modules.admin.reviews.web.dto;

import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

@Slf4j
@Getter
@Setter
public class ReviewMallResponseDto {
    private String type;
    private Long id;
    private String state;
    private String interestCompany;
    private String establishmentTypeValue;
    private String companyName;
    private String userId;
    private String createdAt;
    private String zonecode;
/*
순서 맞춰야함!!!!! 생성자에 들어오는 매개변수 순서 주의!
 */
    public ReviewMallResponseDto(String type,Long id, String state,String interestCompany, String establishmentTypeValue, String companyName, String userId, String createdAt,String zonecode) {
        this.type = type;
        this.id = id;
        this.state= state;
        this.interestCompany = interestCompany;
        this.establishmentTypeValue = establishmentTypeValue;
        this.companyName = companyName;
        this.userId = userId;
        this.createdAt = createdAt;
        this.zonecode =zonecode;
    }
}
