package com.onz.modules.admin.companies.web.dto;

import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CompaniesFixResponseDto {

    @Getter
    private Long id;
    //기관유형
    private InterestCompany interestCompany;
    private String interestCompanyName;
    //설립유형
    private EstablishmentType establishmentType;
    private String establishmentTypeName;
    //기관면
    @Getter
    private String officeName;
    //지역
    @Getter
    private String zonecode;
    //요청자id
    @Getter
    private String userId;
    //요청일

    private ZonedDateTime requestEdt;
    private String requestEdtStr;

    private State state;
    private String stateName;

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
    public String getRequestEdt(){
        String requestEdtStr = "";
        if(this.requestEdt!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            requestEdtStr = this.requestEdt.format(formatter);
        }
        return requestEdtStr;
    }
    public String getState(){
        String isDeleteN ="";
        if(this.state!=null&& this.stateName==null){
            isDeleteN=this.state.name();
        }return isDeleteN;
    }
}
