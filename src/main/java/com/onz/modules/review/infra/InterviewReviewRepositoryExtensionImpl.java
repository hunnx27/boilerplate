package com.onz.modules.review.infra;

import com.onz.modules.company.domain.Company;
import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.QInterviewReview;
import com.onz.modules.company.web.dto.reponse.InterviewListResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class InterviewReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements InterviewReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public InterviewReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Company.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
    public List<InterviewListResponseDto> ListInterview(List<InterviewReview> interviewReviews) {
        QInterviewReview qInterviewReview = QInterviewReview.interviewReview;

        List<InterviewReview> list = jpaQueryFactory
                .selectFrom(qInterviewReview)
                .fetch();
        return list.stream().map(com -> new InterviewListResponseDto(com)).collect(Collectors.toList());
    }
}