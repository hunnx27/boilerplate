package com.onz.modules.company.web.dto.reponse;

import com.onz.common.enums.InterestCompany;
import com.onz.common.enums.InterestCompanyConverter;
import com.onz.common.enums.YN;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDetailResponse {
    @JsonIgnore
    private Long id;
    private InterestCompany interestCompany;
    private EstablishmentType establishmentType;
    private String officeName;
    private String juso;
    private String run;
    private String director;
    private ZonedDateTime openDt;
    @Enumerated(EnumType.STRING)
    private YN useYn;
    @Enumerated(EnumType.STRING)
    private YN evaluateYn;
    private Double fill;
    private long totPeople;
    private long currPeople;
    private String agePeoples;
    private String charItems;
    private String perItems;
    private String evalItems;
    private String zonecode;
    private String zipcode;
    private String phoneNum;
    private String faxNum;
    private String homepage;
    private String syncCode;
    private ZonedDateTime eventBannerDate;

        public CompanyDetailResponse(Long id, InterestCompany interestCompany, EstablishmentType establishmentType,String officeName, String juso, String run,
                                     String director, ZonedDateTime openDt, YN useYn, YN evaluateYn, Double fill, long totPeople,
                                     long currPeople, String agePeoples, String charItems, String perItems, String evalItems,
                                     String zonecode, String zipcode, String phoneNum, String faxNum, String homepage, String syncCode){
        this.id=id;
        this.interestCompany=interestCompany;
        this.establishmentType=establishmentType;
        this.officeName=officeName;
        this.juso=juso;
        this.run=run;
        this.director=director;
        this.openDt=openDt;
        this.useYn=useYn;
        this.evaluateYn=evaluateYn;
        this.fill=fill;
        this.totPeople=totPeople;
        this.currPeople=currPeople;
        this.agePeoples=agePeoples;
        this.charItems=charItems;
        this.perItems=perItems;
        this.evalItems=evalItems;
        this.zonecode=zonecode;
        this.zipcode=zipcode;
        this.phoneNum=phoneNum;
        this.faxNum=faxNum;
        this.homepage=homepage;
        this.syncCode=syncCode;

    }

    public CompanyDetailResponse(Company company){
        this.id=company.getId();
        this.interestCompany=company.getInterestCompany();
        this.establishmentType=company.getEstablishmentType();
        this.officeName=company.getOfficeName();
        this.juso=company.getJuso();
        this.run=company.getRun();
        this.director=company.getDirector();
        this.openDt=company.getOpenDt();
        this.useYn=company.getUseYn();
        this.evaluateYn=company.getEvaluateYn();
        this.fill=company.getFill();
        this.totPeople=company.getTotPeople();
        this.currPeople=company.getCurrPeople();
        this.agePeoples=company.getAgePeoples();
        this.charItems=company.getCharItems();
        this.perItems=company.getPerItems();
        this.evalItems=company.getEvalItems();
        this.zonecode=company.getZonecode();
        this.zipcode=company.getZipcode();
        this.phoneNum=company.getPhoneNum();
        this.faxNum=company.getFaxNum();
        this.homepage=company.getHomepage();
        this.syncCode=company.getSyncCode();
        this.eventBannerDate=company.getEventBannerDate();

    }
}
