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
    private String itemB1;
    private String itemB2;
    private String itemB3;
    private String itemC1;
    private String itemC2;
    private String itemC3;
    private String itemD1;
    private String itemD2;

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
        this.itemB1=companyReview.getItemB1();
        this.itemB2=companyReview.getItemB2();
        this.itemB3=companyReview.getItemB3();
        this.itemC1=companyReview.getItemC1();
        this.itemC2=companyReview.getItemC2();
        this.itemC3=companyReview.getItemC3();
        this.itemD1=companyReview.getItemD1();
        this.itemD2=companyReview.getItemD2();

    }
}
