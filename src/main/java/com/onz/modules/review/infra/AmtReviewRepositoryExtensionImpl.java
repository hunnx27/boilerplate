package com.onz.modules.review.infra;

import com.onz.modules.review.domain.QYearAmtReview;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.web.dto.YearAmtListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class AmtReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements AmtReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public AmtReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(YearAmtReview.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
    public List<YearAmtListResponseDto> ListAmt(List<YearAmtReview> yearAmtReview) {
        QYearAmtReview qYearAmtReview = QYearAmtReview.yearAmtReview;
        return jpaQueryFactory
                .selectFrom(qYearAmtReview)
                .select(Projections.constructor(YearAmtListResponseDto.class,
                        qYearAmtReview.workExp, qYearAmtReview.workExpOpenYn, qYearAmtReview.company.id,
                        qYearAmtReview.amt, qYearAmtReview.endAtmYn, qYearAmtReview.etcItems,
                        qYearAmtReview.company.officeName, qYearAmtReview.mapsidogunguName,
                        qYearAmtReview.id,qYearAmtReview.company.zonecode,qYearAmtReview.etcYn,
                        qYearAmtReview.company.establishmentType,qYearAmtReview.etcAmt))
                .fetch();
    }
}

