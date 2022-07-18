package com.onz.modules.common.pointHistory.infra;

import com.onz.common.enums.YN;
import com.onz.modules.common.pointHistory.domain.PointHistory;
import com.onz.modules.pointHistory.domain.QPoint;
import com.onz.modules.common.pointHistory.web.dto.request.PointHistorySearchRequest;
import com.onz.modules.common.pointHistory.web.dto.request.PointHistoryUpdateRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

public class PointHistoryRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        PointHistoryRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public PointHistoryRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(PointHistory.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public PageImpl<PointHistory> list(PointHistorySearchRequest pointSearchRequest) {
        QPoint point = QPoint.point;

        final Pageable pageable = pointSearchRequest.getPageable();

        BooleanBuilder where = new BooleanBuilder();
        where.and(point.isDelete.eq(YN.N));

        if (StringUtils.hasText(pointSearchRequest.getCode())) {
            where.and(point.code.eq(pointSearchRequest.getCode()));
        }
        if (StringUtils.hasText(pointSearchRequest.getName())) {
            where.and(point.name.eq(pointSearchRequest.getName()));
        }
        if (StringUtils.hasText(pointSearchRequest.getAddress())) {
            where.and(
                point.address.city.like("%" + pointSearchRequest.getAddress() + "%")
                    .or(point.address.street.like(
                        "%" + pointSearchRequest.getAddress() + "%")));
        }

        JPQLQuery<PointHistory> result = from(point)
            .where(where);

        JPQLQuery<PointHistory> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<PointHistory> fetchResults = query.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

}
