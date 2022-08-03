package com.onz.modules.counsel.infra.counsel;

import com.onz.common.enums.YN;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.QCounsel;
import com.onz.modules.counsel.domain.enums.CounselState;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.ZonedDateTime;
import java.util.List;

public class CounselRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        CounselRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public CounselRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Counsel.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Counsel> findAnswerList(Long parentCounselId,Pageable pageable){
        QCounsel counsel = QCounsel.counsel;
        BooleanBuilder where = new BooleanBuilder();
        where.and(counsel.qnaGubn.eq(QnaGubn.A));
        where.and(counsel.parentCounsel.id.eq(parentCounselId));
        JPQLQuery<Counsel> result = from(counsel).where(where);
        JPQLQuery<Counsel> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<Counsel> fetchResults = query.fetchResults();
        return fetchResults.getResults();
    }

    @Override
    public List<Counsel> findCounselList(Pageable pageable) {
        QCounsel counsel = QCounsel.counsel;
        BooleanBuilder where = new BooleanBuilder();
        where.and(counsel.qnaGubn.eq(QnaGubn.Q));
        where.and(counsel.openYn.eq(YN.Y));
        where.and(counsel.isDelete.eq(YN.N));
        where.and(counsel.shortOpenYn.eq(YN.N).or(counsel.createdAt.goe(ZonedDateTime.now().minusDays(1))));
        JPQLQuery<Counsel> result = from(counsel).where(where);
        JPQLQuery<Counsel> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<Counsel> fetchResults = query.fetchResults();
        return fetchResults.getResults();
    }

    @Override
    public long countAdoptedAnswer(Long answerId, Long accountId) {
        QCounsel counsel = QCounsel.counsel;
        BooleanBuilder where = new BooleanBuilder();
        where.and(counsel.counselState.eq(CounselState.A)); // 채택된 글이고
        where.and(counsel.account.id.eq(accountId)); //답변작성자 글이고
        where.and(counsel.qnaGubn.eq(QnaGubn.A)); // 답변이고
        where.and(counsel.isDelete.eq(YN.N)); // 삭제가 안된글이고
        JPQLQuery<Counsel> result = from(counsel).where(where);
        return result.fetchCount();
    }
}
