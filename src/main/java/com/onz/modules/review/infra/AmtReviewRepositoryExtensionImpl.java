package com.onz.modules.review.infra;

import com.onz.modules.review.domain.YearAmtReview;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class AmtReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements AmtReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public AmtReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(YearAmtReview.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
