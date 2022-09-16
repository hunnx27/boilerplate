package com.onz.modules.company.domain;

import com.onz.common.enums.*;
import com.onz.modules.account.domain.Account;
import com.onz.common.domain.BaseEntity;

import java.time.ZonedDateTime;

import com.onz.modules.company.domain.enums.CharItem;
import com.onz.modules.company.domain.enums.CharItemConverter;
import com.onz.modules.company.domain.enums.EstablishmentType;
import com.onz.modules.company.domain.enums.EstablishmentTypeConverter;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.domain.dto.ReviewAllDto;
import com.onz.modules.review.domain.embed.Images;
import com.onz.modules.review.domain.embed.Items;
import com.onz.modules.review.domain.enums.ItemCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SqlResultSetMapping(

        name = "reviewUnion",
        classes = @ConstructorResult(targetClass = ReviewAllDto.class,
                columns = {
                        @ColumnResult(name = "type", type = String.class),
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "accountId", type = Long.class),
                        @ColumnResult(name = "isDelete", type = String.class),
                        @ColumnResult(name = "modifiedAt", type = ZonedDateTime.class),
                        @ColumnResult(name = "state", type = String.class),
                        @ColumnResult(name = "workExpOpenYn", type = String.class),
                        @ColumnResult(name = "createdAt", type = ZonedDateTime.class),
                        @ColumnResult(name = "companyId", type = Long.class),
                        @ColumnResult(name = "companyName", type = String.class),
                        @ColumnResult(name = "establishmentTypeValue",type=String.class),
                        @ColumnResult(name = "txt",type = String.class),
                        @ColumnResult(name = "item_1",type= String.class),
                        @ColumnResult(name = "item_2",type= String.class),
                        @ColumnResult(name = "item_3",type= String.class),
                        @ColumnResult(name = "item_4",type= String.class),
                        @ColumnResult(name = "item_5",type= String.class),
                        @ColumnResult(name = "item_6",type= String.class),
                        @ColumnResult(name = "topQ1",type = String.class),
                        @ColumnResult(name = "txtAdmin",type =String.class),
                        @ColumnResult(name = "workExp",type = Long.class),
                        @ColumnResult(name = "zonecode",type = String.class),
                        @ColumnResult(name = "amt",type = Long.class),
                        @ColumnResult(name = "amtOld",type = Long.class),
                        @ColumnResult(name = "apprDt",type = ZonedDateTime.class),
                        @ColumnResult(name = "apprId",type = String.class),
                        @ColumnResult(name = "apprTxt",type = String.class),
                        @ColumnResult(name = "endAtmYn",type= String.class),
                        @ColumnResult(name = "etcAmt",type = String.class),
//                        @ColumnResult(name = "etcItems",type= String.class),
//                        @ColumnResult(name = "etcYn",type= String.class),
//                        @ColumnResult(name = "mapsidogunguName",type = String.class),
//                        @ColumnResult(name = "topchoiceDt",type = ZonedDateTime.class),
//                        @ColumnResult(name = "topchoiceYn",type = String.class),
//                        @ColumnResult(name = "annYn",type = String.class),
//                        @ColumnResult(name = "image1",type = String.class),
//                        @ColumnResult(name = "image2",type= String.class),
//                        @ColumnResult(name = "image3",type= String.class),
//                        @ColumnResult(name = "image4",type= String.class),
//                        @ColumnResult(name = "image5",type= String.class),
//                        @ColumnResult(name = "itemB1",type= String.class),
//                        @ColumnResult(name = "itemB2",type= String.class),
//                        @ColumnResult(name = "itemB3",type= String.class),
//                        @ColumnResult(name = "itemC1",type= String.class),
//                        @ColumnResult(name = "itemC2",type= String.class),
//                        @ColumnResult(name = "itemC3",type= String.class),
//                        @ColumnResult(name = "itemD1",type= String.class),
//                        @ColumnResult(name = "itemD2",type= String.class),
//                        @ColumnResult(name = "likeCode",type= String.class),

                })
)
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
    @Convert(converter = CharItemConverter.class)
    private List<CharItem> charItems;
    private String perItems;
    private String evalItems;
    private String zonecode;
    private String zipcode;
    private String phoneNum;
    private String faxNum;
    private String homepage;
    private String syncCode;
}
