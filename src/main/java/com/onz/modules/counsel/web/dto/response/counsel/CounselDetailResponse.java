package com.onz.modules.counsel.web.dto.response.counsel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onz.common.enums.YN;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.Counsel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class CounselDetailResponse {

    private Long id;
    private String counselStateCode;
    private String counselStateName;
    private String gubnName;
    private String addedTagData;
    private String txt;
    private String createDate;
    private long reportCnt;
    private Long accountId;
    @JsonProperty(value="isMine")
    private Boolean isMine;

    private String interestOrgName;
    private String interestOrgDesc;
    private String relatedZone;
    private String si;
    private String gugun;
    private String qnaItem;
    private String shortOpenYn;
    private String opneYn;
    private String image5;
    private String image4;
    private String image3;
    private String image2;
    private String image1;




    public CounselDetailResponse(Counsel counsel, Account me) {
        this.id = counsel.getId();
        this.counselStateCode = counsel.getCounselState()!=null ? counsel.getCounselState().name() : "";
        this.counselStateName = counsel.getCounselState()!=null ? counsel.getCounselState().getName() : "";
        this.gubnName = counsel.getGubn()!=null? counsel.getGubn().getName() : "";
        this.txt = counsel.getTxt();
        this.reportCnt = counsel.getReportCnt();
        this.addedTagData = counsel.getInputTag();
        this.createDate = counsel.getCreatedAt()!=null? counsel.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) : "";
        this.accountId = counsel.getAccount().getId();
        this.isMine = (counsel.getAccount().getId() == me.getId());
        this.interestOrgName = counsel.getInterestOrg()!=null? counsel.getInterestOrg().name() : null;
        this.interestOrgDesc = counsel.getInterestOrg()!=null? counsel.getInterestOrg().getDesc() : null;
        this.relatedZone = counsel.getRelatedZone();
        this.si = counsel.getRelatedZone();
        this.gugun = counsel.getRelatedZone();
        this.qnaItem = counsel.getQnaItem()!=null? counsel.getQnaItem().name() : "Q1";
        if(counsel.getImages()!=null){
            this.image1 = counsel.getImages().getImage1();
            this.image2 = counsel.getImages().getImage2();
            this.image3 = counsel.getImages().getImage3();
            this.image4 = counsel.getImages().getImage4();
            this.image5 = counsel.getImages().getImage5();
        }

        this.opneYn = counsel.getOpenYn().name();
        this.shortOpenYn = counsel.getShortOpenYn()!=null? counsel.getShortOpenYn().name() : "Y";
    }

    public CounselDetailResponse(Counsel counsel) {
        this.id = counsel.getId();
        this.counselStateCode = counsel.getCounselState()!=null ? counsel.getCounselState().name() : "";
        this.counselStateName = counsel.getCounselState()!=null ? counsel.getCounselState().getName() : "";
        this.gubnName = counsel.getGubn()!=null? counsel.getGubn().getName() : "";
        this.txt = counsel.getTxt();
        this.reportCnt = counsel.getReportCnt();
        this.addedTagData = counsel.getInputTag();
        this.createDate = counsel.getCreatedAt()!=null? counsel.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) : "";
        this.accountId = counsel.getAccount().getId();
        this.isMine = null;
        this.interestOrgName = counsel.getInterestOrg()!=null? counsel.getInterestOrg().name() : null;
        this.interestOrgDesc = counsel.getInterestOrg()!=null? counsel.getInterestOrg().getDesc() : null;
        this.relatedZone = counsel.getRelatedZone();
        this.si = counsel.getRelatedZone();
        this.gugun = counsel.getRelatedZone();
        this.qnaItem = counsel.getQnaItem()!=null? counsel.getQnaItem().name() : "Q1";
        if(counsel.getImages()!=null){
            this.image1 = counsel.getImages().getImage1();
            this.image2 = counsel.getImages().getImage2();
            this.image3 = counsel.getImages().getImage3();
            this.image4 = counsel.getImages().getImage4();
            this.image5 = counsel.getImages().getImage5();
        }

        this.opneYn = counsel.getOpenYn().name();
        this.shortOpenYn = counsel.getShortOpenYn()!=null? counsel.getShortOpenYn().name() : "Y";
    }
}
