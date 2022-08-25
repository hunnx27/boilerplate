package com.onz.modules.company.domain;

import com.onz.common.enums.*;
import com.onz.modules.account.domain.Account;
import com.onz.common.domain.BaseEntity;

import java.time.ZonedDateTime;

import com.onz.modules.company.domain.enums.EstablishmentType;
import com.onz.modules.company.domain.enums.EstablishmentTypeConverter;
import com.onz.modules.review.domain.YearAmtReview;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Company extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Convert(converter = InterestCompanyConverter.class)
    private InterestCompany interestCompany;
    //추가
//    @Enumerated(EnumType.STRING)
    @Convert(converter = EstablishmentTypeConverter.class)
    EstablishmentType establishmentType;
    private ZonedDateTime eventBannerDate;
    private String officeName;
    private String juso;
    private String gps_x;
    private String gps_y;
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

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
    private List<YearAmtReview> yearAmtReviews = new ArrayList<>();
//    @OneToMany(mappedBy = "id", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
//    private List<YearAmtReview> yearAmtReviews;
}
