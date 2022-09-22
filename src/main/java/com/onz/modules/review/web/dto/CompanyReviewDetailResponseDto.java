package com.onz.modules.review.web.dto;

import com.onz.modules.review.domain.CompanyReview;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class CompanyReviewDetailResponseDto {

    private final String keepWork;
    private final String eventCntJumsu;
    private final String docWrokJumsu;
    private final String readyClassJumsu;
    private final String personalPcJumsu;
    private final String selfDevJumsu;
    private final String kizRestJumsu;
    private final String partnershipJumsu;
    private final String ownerLeadershipJumsu;
    public Long workExp; // 근무연차

    private final String Txt;
    private final double starJumsuTotalAvg;
    private final int starJumsuWorkload;
    private final int starJumsuJobSatisfaction;
    private final int starJumsuWorkAtmosphere;
    private final String createDate;

    public CompanyReviewDetailResponseDto(CompanyReview companyReview) {
        this.Txt = companyReview.getTxt();
        this.keepWork = companyReview.getLikeCode().getName();
        this.eventCntJumsu = companyReview.getItemB1().getName();
        this.docWrokJumsu = companyReview.getItemB2().getName();
        this.readyClassJumsu = companyReview.getItemB3().getName();
        this.personalPcJumsu = companyReview.getItemC1().getName();
        this.selfDevJumsu = companyReview.getItemC2().getName();
        this.kizRestJumsu = companyReview.getItemC3().getName();
        this.partnershipJumsu = companyReview.getItemD1().getName();
        this.ownerLeadershipJumsu=companyReview.getItemD2().getName();
        this.starJumsuTotalAvg = companyReview.getStarJumsuTotalAvg();
        this.starJumsuWorkload = companyReview.getStarJumsuWorkload();
        this.starJumsuJobSatisfaction = companyReview.getStarJumsuJobSatisfaction();
        this.starJumsuWorkAtmosphere = companyReview.getStarJumsuWorkAtmosphere();
        this.workExp= companyReview.getWork_exp();
        this.createDate = companyReview.getCreatedAt()!=null? companyReview.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";

    }
}
