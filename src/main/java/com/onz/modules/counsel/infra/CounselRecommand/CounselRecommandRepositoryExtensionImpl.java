package com.onz.modules.counsel.infra.CounselRecommand;

import com.onz.common.enums.YN;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.CounselRecommand;
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

public class CounselRecommandRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        CounselRecommandRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public CounselRecommandRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(CounselRecommand.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
