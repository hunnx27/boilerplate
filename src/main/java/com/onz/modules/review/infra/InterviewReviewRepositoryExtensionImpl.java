package com.onz.modules.review.infra;

import com.onz.modules.company.domain.Company;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class InterviewReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements InterviewReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public InterviewReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Company.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}