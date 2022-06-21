package com.onz.modules.education.infra;

import com.onz.modules.account.domain.QAccount;
import com.onz.modules.education.web.dto.request.EducationSearchRequest;
import com.onz.modules.education.domain.Education;
import com.onz.modules.education.domain.QEducation;
import com.onz.common.enums.YN;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class EducationRepositoryExtensionImpl extends QuerydslRepositorySupport implements
    EducationRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    public EducationRepositoryExtensionImpl(JPAQueryFactory queryFactory) {
        super(Education.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Education> educations(EducationSearchRequest educationSearchRequest,
        Pageable pageable) {
        QEducation education = QEducation.education;
        BooleanBuilder where = new BooleanBuilder();
        where.and(education.isDelete.eq(YN.N));

        if (StringUtils.hasText(educationSearchRequest.getName())) {
            where.or(education.name.containsIgnoreCase(educationSearchRequest.getName()));
        }
        if (StringUtils.hasText(educationSearchRequest.getSubject())) {
            where.or(education.subject.containsIgnoreCase(educationSearchRequest.getSubject()));
        }

        JPQLQuery<Education> result = from(education)
            .leftJoin(education.accounts, QAccount.account).fetchJoin()
            .where(where);

        JPQLQuery<Education> query = Objects
            .requireNonNull(getQuerydsl())
            .applyPagination(pageable, result);

        QueryResults<Education> queryResults = query.fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    @Override
    public boolean deleteEducation(Long id) {
        QEducation education = QEducation.education;
        return update(education)
            .set(education.isDelete, YN.Y)
            .where(education.isDelete.eq(YN.N)
                .and(education.id.eq(id)))
            .execute() == 1L;
    }


}
