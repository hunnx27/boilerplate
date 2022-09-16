package com.onz.modules.review.domain.dto;

import com.onz.common.enums.InterestCompanyConverter;
import com.onz.common.enums.YN;
import com.onz.modules.company.domain.enums.EstablishmentType;
import com.onz.modules.company.domain.enums.EstablishmentTypeConverter;
import com.onz.modules.review.domain.enums.ItemCode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Convert;
import javax.persistence.SqlResultSetMapping;
import java.time.ZonedDateTime;

@Slf4j
@Getter
@Setter
public class ReviewAllDto {
    private String type;
    private Long id;
    private Long accountId;
    private String isDelete;
    private ZonedDateTime modifiedAt;
    private String state;
    private YN workExpOpenYn;
    private ZonedDateTime createAt;
    private String item_1;
    private YN item_2;
    private YN item_3;
    private String item_4;
    private String item_5;
    private YN item_6;
    private String topQ1;
    private Long companyId;
    private String companyName;
    private String establishmentTypeValue;
    private Long workExp;
    private String zonecode;
    private Long amt;
    private String etcAmt;
    private String etcItems;
    private String mapsidogunguName;
    private ItemCode itemB1;
    private ItemCode itemB2;
    private ItemCode itemB3;
    private ItemCode itemC1;
    private ItemCode itemC2;
    private ItemCode itemC3;
    private ItemCode itemD1;
    private ItemCode itemD2;
    private ItemCode likeCode;
    private String impCost;
    private String txt;
    private String txtAdming;
    private Long amtOld;
    private ZonedDateTime apprDt;
    private String apprId;
    private String apprTxt;
    private String ectAmt;
    private YN endAtmYn;
    private YN etcYn;
    private ZonedDateTime topchoiceDt;
    private YN topchoiceYn;
    private YN annYn;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;

    public ReviewAllDto(String type, Long id, Long accountId, String isDelete, ZonedDateTime modifiedAt,
                        String state, String workExpOpenYn
            , ZonedDateTime createAt, Long companyId
            , String companyName
            ,
                        String establishmentTypeValue, String txt
            , String item_1, String item_2, String item_3, String item_4, String item_5, String item_6,
                        String topQ1, String txtAdmin, Long workExp, String zonecode, Long amt
            , Long amtOld, ZonedDateTime apprDt, String apprId, String apprTxt,
                        String endAtmYn, String etcAmt
            ,
                        String etcItems, String etcYn, String mapsidogunguName,
                        ZonedDateTime topchoiceDt, String topchoiceYn, String annYn,
                        String image1, String image2, String image3, String image4,
                        String image5, String itemB1, String itemB2, String itemB3,
                        String itemC1, String itemC2, String itemC3, String itemD1
            , String itemD2, String likeCode
    ) {
        log.info("##### id : {}", id);
        this.type = type;
        this.id = id;
        this.accountId = accountId;
        this.isDelete = isDelete;
        this.state = state;
        this.workExpOpenYn = YN.valueOf(workExpOpenYn);
        this.modifiedAt = modifiedAt;
        this.createAt = createAt;
        this.companyId = companyId;
        this.companyName = companyName;
        this.establishmentTypeValue =establishmentTypeValue;
        this.item_1 = item_1;
        this.item_2 = item_2 != null ? YN.valueOf(item_2) : YN.N;
        this.item_3 = item_3 != null ? YN.valueOf(item_3) : YN.N;
        this.item_4 = item_4;
        this.item_5 = item_5;
        this.item_6 = item_6 != null ? YN.valueOf(item_6) : YN.N;
        this.topQ1 = topQ1;
        this.txtAdming = txtAdmin;
        this.workExp = workExp;
        this.zonecode = zonecode;
        this.amt = amt;
        this.amtOld = amtOld;
        this.apprDt = apprDt;
        this.apprId = apprId;
        this.apprTxt = apprTxt;
        this.endAtmYn = endAtmYn != null ? YN.valueOf(endAtmYn) : YN.N;
        this.etcAmt = etcAmt;
        this.etcItems = etcItems;
        this.etcYn = etcYn != null ? YN.valueOf(endAtmYn) : YN.N;
        this.mapsidogunguName = mapsidogunguName;
        this.topchoiceDt = topchoiceDt;
        this.topchoiceYn = topchoiceYn != null ? YN.valueOf(topchoiceYn) : YN.N;
        this.annYn = annYn != null ? YN.valueOf(annYn) : YN.N;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
        this.itemB1 = ItemCode.valueOf(itemB1);
        this.itemB2 = ItemCode.valueOf(itemB2);
        this.itemB3 = ItemCode.valueOf(itemB3);
        this.itemC1 = ItemCode.valueOf(itemC1);
        this.itemC2 = ItemCode.valueOf(itemC2);
        this.itemC3 = ItemCode.valueOf(itemC3);
        this.itemD1 = ItemCode.valueOf(itemD1);
        this.itemD2 = ItemCode.valueOf(itemD2);
        this.likeCode = ItemCode.valueOf(likeCode);
        this.txt = txt;
    }

    //
    //    String getTxtAdmin();
    //    Long getAmtOld();
//    String getApprDt();
//    String getApprTxt();
//    String getApprId();
//    String getEndAtmYn();
    //    String getEtcYn();
    //    String getTopchoiceDt();
//    YN getTopchoiceYn();
//    YN getAnnYn();
//    String getImage1();
//    String getImage2();
//    String getImage3();
//    String getImage4();
//    String getImage5();
//    String getWorkCost();
//    String getAddCost();
//    String getEtcCost();
//    Long getTotalCost();
}
