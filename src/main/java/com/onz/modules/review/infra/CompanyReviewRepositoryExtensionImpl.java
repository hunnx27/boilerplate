package com.onz.modules.review.infra;

import com.onz.modules.review.domain.*;
import com.onz.modules.review.web.dto.CompanyReviewListResponseDto;
import com.onz.modules.review.web.dto.InterviewListResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements CompanyReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public CompanyReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(CompanyReview.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
    public List<CompanyReviewListResponseDto> ListCompanyReview(List<CompanyReview> companyReviews) {
        QCompanyReview qCompanyReview = QCompanyReview.companyReview;
        List<CompanyReview> list = jpaQueryFactory
                .selectFrom(qCompanyReview)
                .fetch();
        return list.stream().map(com -> new CompanyReviewListResponseDto(com)).collect(Collectors.toList());
    }
}