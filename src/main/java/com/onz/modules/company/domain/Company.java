package com.onz.modules.company.domain;

import com.onz.common.enums.Gubn;
import com.onz.modules.account.domain.Account;
import com.onz.common.domain.BaseEntity;
import com.onz.common.enums.YN;
import java.time.ZonedDateTime;

import com.onz.modules.company.domain.enums.EstablishmentType;
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
    @Enumerated(EnumType.STRING)
    Gubn gubn;
    @Enumerated(EnumType.STRING)
    EstablishmentType establishmentType;
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
    private ZonedDateTime eventBannerDate;
}
