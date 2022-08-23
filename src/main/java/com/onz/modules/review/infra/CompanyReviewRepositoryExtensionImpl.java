package com.onz.modules.review.infra;

import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.domain.YearAmtReview;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CompanyReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements CompanyReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public CompanyReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(CompanyReview.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}