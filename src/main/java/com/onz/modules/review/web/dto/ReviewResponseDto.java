package com.onz.modules.review.web.dto;

import com.onz.common.enums.YN;
import com.onz.common.web.BaseApiController;
import com.onz.modules.company.domain.enums.EstablishmentType;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.domain.dto.ReviewAll;
import com.onz.modules.review.domain.enums.ItemCode;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumSet;
import java.util.NoSuchElementException;


@Getter
@NoArgsConstructor
public class ReviewResponseDto {

    /**
     * Common
     */
    private String Type;
    private Long id;
    @Setter
    private String mapsidogunguName;
    private Long companyId;
    private String companyName;
    private String establishmentTypeName;
    private Long accountId;
    private Long workExp;
//    private String apprDt;
//    private String apprId;
//    private String apprTxt;
//    private String zonecode;
//    private String txtAdmin;
//    private YN isDelete;
//    private String ModifiedAt;
//    private String state;
//    private String CreatedAt;
//    private YN WorkExpOpenYn;
//    private String topchoiceDt;
//    private YN topchoiceYn;
//    private String image1;
//    private String image2;
//    private String image3;
//    private String image4;
//    private String image5;

    /**
     * Company
     */
    private String Txt;
    private double starJumsuTotalAvg;
    private int starJumsuWorkload;
    private int starJumsuJobSatisfaction;
    private int starJumsuWorkAtmosphere;
//    private String item_b1;
//    private String item_b2;
//    private String item_b3;
//    private String item_c1;
//    private String item_c2;
//    private String item_c3;
//    private String item_d1;
//    private String item_d2;
//    private String likeCode;

    /**
     * Interview
     */
    private String q_1;
    private String itemMood;
    private String itemTest1;
    private String itemTest2;
    private String itemTest3;

    /**
     * AMT
     */
    private Long amt;
    @Setter
    private Long totalCost;
    @Setter
    private String impCost;
    @Setter
    private String workCost;
    @Setter
    private String addCost;
    @Setter
    private String etcCost;
//    private String etcAmt;
//    private String etcItems;
//    private String endAtmYn;
//    private String etcYn;
//    private YN annYn;
//    private Long amtOld;

    public ReviewResponseDto(ReviewAll reviewAll) {
        this.Type =reviewAll.getType();
        this.id = reviewAll.getId();
        this.itemTest1 = reviewAll.getItem_1()!=null? "O" : "X";
        this.itemTest2 = reviewAll.getItem_2()!=null&&"Y".equals(reviewAll.getItem_2())? "O" : "X";
        this.itemTest3 = reviewAll.getItem_3()!=null&&"Y".equals(reviewAll.getItem_3())? "O" : "X";
        switch(reviewAll.getItem_5()!=null?reviewAll.getItem_5():"999"){
            case "1": this.itemMood = "여유";break;
            case "2": this.itemMood = "편안";break;
            case "3": this.itemMood = "긴장";break;
            default: this.itemMood = "-";break;
        }
        this.q_1=reviewAll.getTopQ1();
        this.companyId = reviewAll.getCompanyId();
        this.companyName = reviewAll.getCompanyName();
        EstablishmentType type = EnumSet.allOf(EstablishmentType.class).stream()
                .filter(e->e.getValue().equals(reviewAll.getEstablishmentTypeValue()))
                .findAny().orElse(EstablishmentType.C99);
        this.establishmentTypeName = type.getName();
        this.accountId = reviewAll.getAccountId();
        this.workExp = reviewAll.getWorkExp();
        this.amt = reviewAll.getAmt();

        this.mapsidogunguName = reviewAll.getMapsidogunguName();
        if("COMPANY".equals(reviewAll.getType())){
            CompanyReview r = new CompanyReview(reviewAll);
            this.starJumsuTotalAvg = r.getStarJumsuTotalAvg();
            this.starJumsuWorkload = r.getStarJumsuWorkload();
            this.starJumsuJobSatisfaction =  r.getStarJumsuJobSatisfaction();
            this.starJumsuWorkAtmosphere = r.getStarJumsuWorkAtmosphere();
        }
        this.Txt = reviewAll.getTxt();

//        this.etcAmt = reviewAll.getEtcAmt();
//        this.etcItems = reviewAll.getEtcItems();
//        this.zonecode = reviewAll.getZonecode();
//        this.WorkExpOpenYn = reviewAll.getWorkExpOpenYn();
//        this.state = reviewAll.getState();
//        this.isDelete = reviewAll.getIsDelete();
//        this.ModifiedAt = reviewAll.getModifiedAt();
//        this.CreatedAt = reviewAll.getCreatedAt();
//        this.txtAdmin = reviewAll.getTxtAdmin();
//        this.amtOld = reviewAll.getAmtOld();
//        this.apprDt = reviewAll.getApprDt();
//        this.apprId = reviewAll.getApprId();
//        this.apprTxt = reviewAll.getApprTxt();
//        this.endAtmYn = reviewAll.getEndAtmYn();
//        this.etcYn = reviewAll.getEtcYn();
//        this.topchoiceDt = reviewAll.getTopchoiceDt();
//        this.topchoiceYn = reviewAll.getTopchoiceYn();
//        this.annYn = reviewAll.getAnnYn();
//        this.image1 = reviewAll.getImage1();
//        this.image2 = reviewAll.getImage2();
//        this.image3 = reviewAll.getImage3();
//        this.image4 = reviewAll.getImage4();
//        this.image5 = reviewAll.getImage5();
//        this.item_b1 = reviewAll.getItemB1()!=null? reviewAll.getItemB1().name() : null;
//        this.item_b2 = reviewAll.getItemB2()!=null? reviewAll.getItemB2().name() : null;
//        this.item_b3 = reviewAll.getItemB3()!=null? reviewAll.getItemB3().name() : null;
//        this.item_c1 = reviewAll.getItemC1()!=null? reviewAll.getItemC1().name() : null;
//        this.item_c2 = reviewAll.getItemC2()!=null? reviewAll.getItemC2().name() : null;
//        this.item_c3 = reviewAll.getItemC3()!=null? reviewAll.getItemC3().name() : null;
//        this.item_d1 = reviewAll.getItemD1()!=null? reviewAll.getItemD1().name() : null;
//        this.item_d2 = reviewAll.getItemD2()!=null? reviewAll.getItemD2().name() : null;
//        this.likeCode = reviewAll.getLikeCode()!=null? reviewAll.getLikeCode().name() : null;
//        this.impCost = reviewAll.getImpCost();
//        this.workCost = reviewAll.getWorkCost();
//        this.addCost = reviewAll.getAddCost();
//        this.etcCost = reviewAll.getEtcCost();
//        this.totalCost = reviewAll.getTotalCost();
    }
}