package com.onz.modules.company.infra;

import com.onz.common.enums.YN;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.domain.QCompany;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

public class CompanyRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        CompanyRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public CompanyRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Company.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public PageImpl<Company> list(CompanySearchRequest searchRequest) {
        QCompany company = QCompany.company;

        final Pageable pageable = searchRequest.getPageable();

        BooleanBuilder where = new BooleanBuilder();
        where.and(company.isDelete.eq(YN.N));

        JPQLQuery<Company> result = from(company)
            .where(where);

        JPQLQuery<Company> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<Company> fetchResults = query.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

    @Override
    public void update(CompanyUpdateRequest updateRequest) {
//        QCompany company = QCompany.company;
//        update(company)
//            .set(company.officeName, updateRequest.getName())
//            .where(company.id.eq(updateRequest.getId()))
//            .execute();
    }
}
