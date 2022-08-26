package com.onz.modules.review.infra;

import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.web.dto.InterviewListResponseDto;
import com.onz.modules.review.web.dto.YearAmtListResponseDto;

import java.util.List;

public interface InterviewReviewRepositoryExtension {
    List<InterviewListResponseDto> ListInterview(List<InterviewReview> interviewReviews);
}
