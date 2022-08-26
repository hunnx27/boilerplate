package com.onz.modules.review.web.dto;

import com.onz.common.enums.YN;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.domain.InterviewReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Column;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CompanyReviewListResponseDto {
    private String type="COMPANY";
    @JsonIgnore
    private Long id;
    private Long work_exp;
    private YN workExpOpenYn;
    private String Zonecode;
    private String mapsidogunguName;
    private String establishmentType;
    private String officeName;
    private String txt;
    private ZonedDateTime createdAt;
    private String likeCode;
    private String itemB1;
    private String itemB2;
    private String itemB3;
    private String itemC1;
    private String itemC2;
    private String itemC3;
    private String itemD1;
    private String itemD2;
    private String likeCodeName;
    private String itemB1Name;
    private String itemB2Name;
    private String itemB3Name;
    private String itemC1Name;
    private String itemC2Name;
    private String itemC3Name;
    private String itemD1Name;
    private String itemD2Name;

    public CompanyReviewListResponseDto(CompanyReview companyReview){
        this.id=companyReview.getId();
        this.officeName=companyReview.getCompany().getOfficeName();
        this.work_exp= companyReview.getWork_exp();
        this.establishmentType=companyReview.getCompany().getEstablishmentType().getName();
        this.mapsidogunguName=getMapsidogunguName();
        this.Zonecode=companyReview.getCompany().getZonecode();
        this.workExpOpenYn=companyReview.getWorkExpOpenYn();
        this.txt=companyReview.getTxt();
        this.createdAt=companyReview.getCreatedAt();
        this.likeCode=companyReview.getLikeCode().name();
        this.itemB1=companyReview.getItemB1().name();
        this.itemB2=companyReview.getItemB2().name();
        this.itemB3=companyReview.getItemB3().name();
        this.itemC1=companyReview.getItemC1().name();
        this.itemC2=companyReview.getItemC2().name();
        this.itemC3=companyReview.getItemC3().name();
        this.itemD1=companyReview.getItemD1().name();
        this.itemD2=companyReview.getItemD2().name();
        this.likeCodeName=companyReview.getLikeCode().getName();
        this.itemB1Name=companyReview.getItemB1().getName();
        this.itemB2Name=companyReview.getItemB2().getName();
        this.itemB3Name=companyReview.getItemB3().getName();
        this.itemC1Name=companyReview.getItemC1().getName();
        this.itemC2Name=companyReview.getItemC2().getName();
        this.itemC3Name=companyReview.getItemC3().getName();
        this.itemD1Name=companyReview.getItemD1().getName();
        this.itemD2Name=companyReview.getItemD2().getName();

    }
}
