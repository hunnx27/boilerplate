package com.onz.modules.counsel.infra.counselComment;

import com.onz.modules.counsel.domain.CounselComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CounselCommentRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        CounselCommentRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public CounselCommentRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(CounselComment.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

}
