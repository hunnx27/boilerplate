package com.onz.modules.review.infra;

import com.onz.modules.company.domain.Company;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        ReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public ReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Company.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

}
