package com.onz.modules.counsel.infra.counselRecommand;

import com.onz.modules.counsel.domain.CounselRecommand;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CounselRecommandRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        CounselRecommandRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public CounselRecommandRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(CounselRecommand.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
