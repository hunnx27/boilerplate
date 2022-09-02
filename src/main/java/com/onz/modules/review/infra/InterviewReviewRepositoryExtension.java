package com.onz.modules.review.infra;

import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.company.web.dto.reponse.InterviewListResponseDto;

import java.util.List;

public interface InterviewReviewRepositoryExtension {
    List<InterviewListResponseDto> ListInterview(List<InterviewReview> interviewReviews);
}
