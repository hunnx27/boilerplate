package com.onz.modules.counsel.infra.Counsel;

import com.onz.common.enums.YN;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.QCounsel;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.organization.domain.Organization;
import com.onz.modules.organization.domain.QOrganization;
import com.onz.modules.organization.web.dto.request.OrganizationSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

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
}
