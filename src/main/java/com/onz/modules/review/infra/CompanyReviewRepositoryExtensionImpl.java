package com.onz.modules.review.infra;

import com.onz.common.enums.YN;
import com.onz.modules.review.domain.*;
import com.onz.modules.company.web.dto.reponse.CompanyReviewListResponseDto;
import com.querydsl.core.BooleanBuilder;
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

    @Override
    public List<CompanyReviewListResponseDto> listCompanyReview(List<CompanyReview> companyReviews) {
        QCompanyReview qCompanyReview = QCompanyReview.companyReview;
        List<CompanyReview> list = jpaQueryFactory
                .selectFrom(qCompanyReview)
                .fetch();
        return list.stream().map(com -> new CompanyReviewListResponseDto(com)).collect(Collectors.toList());
    }

    @Override
    public List<CompanyReview> listCompanyReviewByCompanyId(Long companyId) {
        QCompanyReview qCompanyReview = QCompanyReview.companyReview;
        BooleanBuilder where = new BooleanBuilder();
        where.and(qCompanyReview.isDelete.eq(YN.N));
        where.and(qCompanyReview.company.id.eq(companyId));

        List<CompanyReview> list = jpaQueryFactory
                .selectFrom(qCompanyReview)
                .where(where)
                .fetch();
//        return list.stream().map(com -> new CompanyReviewListResponseDto(com)).collect(Collectors.toList());
        return list;
    }
}