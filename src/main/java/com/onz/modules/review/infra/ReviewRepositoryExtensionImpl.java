package com.onz.modules.review.infra;

import com.onz.modules.company.domain.Company;
import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.QCompanyReview;
import com.onz.modules.review.domain.QInterviewReview;
import com.onz.modules.review.domain.QYearAmtReview;
import com.onz.modules.review.domain.embed.Review;
import com.onz.modules.review.web.dto.InterviewListResponseDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class ReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        ReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public ReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Company.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

}
