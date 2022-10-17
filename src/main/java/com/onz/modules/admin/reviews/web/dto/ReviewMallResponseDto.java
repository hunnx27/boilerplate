package com.onz.modules.admin.reviews.web.dto;

import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Setter
public class ReviewMallResponseDto {
    private String type;
    @Getter
    private Long id;
    @Getter
    private State state;
    private String stateName;
    @Getter
    private InterestCompany interestCompany;
    private String interestCompanyName;
    @Getter
    private EstablishmentType establishmentType;
    private String establishmentTypeName;
    @Getter
    private String officeName;
    @Getter
    private String userId;
    @Getter
    private ZonedDateTime createdAt;
    @Getter
    private ZonedDateTime apprDt;
    @Getter
    private String zonecode;
/*
순서 맞춰야함!!!!! 생성자에 들어오는 매개변수 순서 주의!
 */

public String getInterestCompany() {
    String interestCompanyName = this.interestCompany.getDesc();
    if(this.interestCompany !=null && this.interestCompanyName==null){
        interestCompanyName=this.interestCompany.getDesc();
    }return interestCompanyName;
}

    public String getEstablishmentType() {
        String establishmentTypeName = this.establishmentType.getName();
        if(this.establishmentType !=null&& this.establishmentTypeName==null){
            establishmentTypeName = this.establishmentType.getName();
        }return establishmentTypeName;
    }
    public String getApprDt(){
        String apprAtStr = "";
        if(this.apprDt!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            apprAtStr = this.apprDt.format(formatter);
        }
        return apprAtStr;
    }

    public String getCreatedAt(){
        String createdAtStr = "";
        if(this.createdAt!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            createdAtStr = this.createdAt.format(formatter);
        }
        return createdAtStr;
    }
    public String getStateName() {
        if (this.state != null && this.stateName == null) {
            stateName = this.state.getName();
        }
        return stateName;
    }
}
