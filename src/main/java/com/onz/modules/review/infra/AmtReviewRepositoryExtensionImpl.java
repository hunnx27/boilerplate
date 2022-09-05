package com.onz.modules.review.infra;

import com.onz.modules.company.web.dto.reponse.InterviewListResponseDto;
import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.QYearAmtReview;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.company.web.dto.reponse.YearAmtListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class AmtReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements AmtReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public AmtReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(YearAmtReview.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
    public List<YearAmtListResponseDto> ListAmt(List<YearAmtReview> yearAmtReview) {
        QYearAmtReview qYearAmtReview = QYearAmtReview.yearAmtReview;

        List<YearAmtReview> list = jpaQueryFactory
                .selectFrom(qYearAmtReview)
                .fetch();
        return list.stream().map(com -> new YearAmtListResponseDto(com)).collect(Collectors.toList());

    }
}

