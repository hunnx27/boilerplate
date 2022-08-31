package com.onz.modules.review.domain.dto;

import com.onz.common.enums.YN;
import com.onz.modules.review.domain.enums.ItemCode;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public interface ReviewAll {
    String getType();
    Long getId();
    YN getIsDelete();
    String getModifiedAt();
    String getState();
    String getCreatedAt();
    YN getWorkExpOpenYn();
    String getItem_1();
    YN getItem_2();
    YN getItem_3();
    String getItem_4();
    String getItem_5();
    YN getItem_6();
    String getTopQ1();
    Long getCompanyId();
    Long getAccountId();
    String getTxtAdmin();
    Long getWorkExp();
    String getZonecode();
    Long getAmt();
    Long getAmtOld();
    String getApprDt();
    String getApprTxt();
    String getApprId();
    String getEndAtmYn();
    String getEtcAmt();
    String getEtcItems();
    String getEtcYn();
    String getMapsidogunguName();
    String getTopchoiceDt();
    YN getTopchoiceYn();
    YN getAnnYn();
    String getImage1();
    String getImage2();
    String getImage3();
    String getImage4();
    String getImage5();
    @Enumerated(EnumType.STRING)
    ItemCode getItemB1();
    @Enumerated(EnumType.STRING)
    ItemCode getItemB2();
    @Enumerated(EnumType.STRING)
    ItemCode getItemB3();
    @Enumerated(EnumType.STRING)
    ItemCode getItemC1();
    @Enumerated(EnumType.STRING)
    ItemCode getItemC2();
    @Enumerated(EnumType.STRING)
    ItemCode getItemC3();
    @Enumerated(EnumType.STRING)
    ItemCode getItemD1();
    @Enumerated(EnumType.STRING)
    ItemCode getItemD2();
    @Enumerated(EnumType.STRING)
    ItemCode getLikeCode();
    String getImpCost();
    String getWorkCost();
    String getAddCost();
    String getEtcCost();
    Long getTotalCost();
    String getTxt();

}