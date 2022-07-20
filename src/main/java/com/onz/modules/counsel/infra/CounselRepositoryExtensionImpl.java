package com.onz.modules.counsel.infra;

import com.onz.common.enums.YN;
import com.onz.modules.organization.domain.Organization;
import com.onz.modules.organization.domain.QOrganization;
import com.onz.modules.organization.web.dto.request.OrganizationSearchRequest;
import com.onz.modules.organization.web.dto.request.OrganizationUpdateRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

public class CounselRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        CounselRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public CounselRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Organization.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public PageImpl<Organization> list(OrganizationSearchRequest organizationSearchRequest) {
        QOrganization organization = QOrganization.organization;

        final Pageable pageable = organizationSearchRequest.getPageable();

        BooleanBuilder where = new BooleanBuilder();
        where.and(organization.isDelete.eq(YN.N));

        if (StringUtils.hasText(organizationSearchRequest.getCode())) {
            where.and(organization.code.eq(organizationSearchRequest.getCode()));
        }
        if (StringUtils.hasText(organizationSearchRequest.getName())) {
            where.and(organization.name.eq(organizationSearchRequest.getName()));
        }

        JPQLQuery<Organization> result = from(organization)
            .where(where);

        JPQLQuery<Organization> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<Organization> fetchResults = query.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

    @Override
    public void update(OrganizationUpdateRequest updateRequest) {
        QOrganization organization = QOrganization.organization;
        update(organization)
            .set(organization.name, updateRequest.getName())
            .where(organization.id.eq(updateRequest.getId()))
            .execute();
    }
}
