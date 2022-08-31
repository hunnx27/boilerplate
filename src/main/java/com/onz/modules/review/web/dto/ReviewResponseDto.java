package com.onz.modules.review.web.dto;

import com.onz.common.enums.YN;
import com.onz.common.web.BaseApiController;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.domain.dto.ReviewAll;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ReviewResponseDto extends BaseApiController {
    private String Type;
    private Long id;
    private YN isDelete;
    private String ModifiedAt;
    private String state;
    private String CreatedAt;
    private YN WorkExpOpenYn;
    private String item_1;
    private YN item_2;
    private YN item_3;
    private String item_4;
    private String item_5;
    private YN item_6;
    private Long CompanyId;
    private Long AccountId;
    private String txtAdmin;
    private Long workExp;
    private String Zonecode;
    private Long amt;
    private Long amtOld;
    private String apprDt;
    private String apprId;
    private String apprTxt;
    private String endAtmYn;
    private String etcAmt;
    private String etcItems;
    private String etcYn;
    private String mapsidogunguName;
    private String topchoiceDt;
    private YN topchoiceYn;
    private YN annYn;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
//    private String item_b1;
//    private String item_b2;
//    private String item_b3;
//    private String item_c1;
//    private String item_c2;
//    private String item_c3;
//    private String item_d1;
//    private String item_d2;
    private double starJumsuTotalAvg;
    private int starJumsuWorkload;
    private int starJumsuJobSatisfaction;
    private int starJumsuWorkAtmosphere;
    private String q_1;
    private String likeCode;
    private String Txt;

    private String impCost;
    private String workCost;
    private String addCost;
    private String etcCost;
    private Long totalCost;

    public ReviewResponseDto(ReviewAll reviewAll) {
        this.Type =reviewAll.getType();
        this.id = reviewAll.getId();
        this.isDelete = reviewAll.getIsDelete();
        this.ModifiedAt = reviewAll.getModifiedAt();
        this.state = reviewAll.getState();
        this.CreatedAt = reviewAll.getCreatedAt();
        this.WorkExpOpenYn = reviewAll.getWorkExpOpenYn();
        this.item_1 = reviewAll.getItem_1();
        this.item_2 = reviewAll.getItem_2();
        this.item_3 = reviewAll.getItem_3();
        this.item_4 = reviewAll.getItem_4();
        this.item_5 = reviewAll.getItem_5();
        this.item_6 = reviewAll.getItem_6();
        this.q_1=reviewAll.getTopQ1();
        this.CompanyId = reviewAll.getCompanyId();
        this.AccountId = reviewAll.getAccountId();
        this.txtAdmin = reviewAll.getTxtAdmin();
        this.workExp = reviewAll.getWorkExp();
        this.Zonecode = reviewAll.getZonecode();
        this.amt = reviewAll.getAmt();
        this.amtOld = reviewAll.getAmtOld();
        this.apprDt = reviewAll.getApprDt();
        this.apprId = reviewAll.getApprId();
        this.apprTxt = reviewAll.getApprTxt();
        this.endAtmYn = reviewAll.getEndAtmYn();
        this.etcAmt = reviewAll.getEtcAmt();
        this.etcItems = reviewAll.getEtcItems();
        this.etcYn = reviewAll.getEtcYn();
        this.mapsidogunguName = reviewAll.getMapsidogunguName();
        this.topchoiceDt = reviewAll.getTopchoiceDt();
        this.topchoiceYn = reviewAll.getTopchoiceYn();
        this.annYn = reviewAll.getAnnYn();
        this.image1 = reviewAll.getImage1();
        this.image2 = reviewAll.getImage2();
        this.image3 = reviewAll.getImage3();
        this.image4 = reviewAll.getImage4();
        this.image5 = reviewAll.getImage5();
//        this.item_b1 = reviewAll.getItemB1()!=null? reviewAll.getItemB1().name() : null;
//        this.item_b2 = reviewAll.getItemB2()!=null? reviewAll.getItemB2().name() : null;
//        this.item_b3 = reviewAll.getItemB3()!=null? reviewAll.getItemB3().name() : null;
//        this.item_c1 = reviewAll.getItemC1()!=null? reviewAll.getItemC1().name() : null;
//        this.item_c2 = reviewAll.getItemC2()!=null? reviewAll.getItemC2().name() : null;
//        this.item_c3 = reviewAll.getItemC3()!=null? reviewAll.getItemC3().name() : null;
//        this.item_d1 = reviewAll.getItemD1()!=null? reviewAll.getItemD1().name() : null;
//        this.item_d2 = reviewAll.getItemD2()!=null? reviewAll.getItemD2().name() : null;
//        this.likeCode = reviewAll.getLikeCode()!=null? reviewAll.getLikeCode().name() : null;
        if("COMPANY".equals(reviewAll.getType())){
            CompanyReview r = new CompanyReview(reviewAll);
            this.starJumsuTotalAvg = r.getStarJumsuTotalAvg();
            this.starJumsuWorkload = r.getStarJumsuWorkload();
            this.starJumsuJobSatisfaction =  r.getStarJumsuJobSatisfaction();
            this.starJumsuWorkAtmosphere = r.getStarJumsuWorkAtmosphere();
        }
        this.Txt = reviewAll.getTxt();
//        this.impCost = reviewAll.getImpCost();
//        this.workCost = reviewAll.getWorkCost();
//        this.addCost = reviewAll.getAddCost();
//        this.etcCost = reviewAll.getEtcCost();
//        this.totalCost = reviewAll.getTotalCost();
    }
}