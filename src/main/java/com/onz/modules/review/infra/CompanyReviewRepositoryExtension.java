package com.onz.modules.review.infra;

import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.web.dto.CompanyReviewListResponseDto;
import com.onz.modules.review.web.dto.InterviewListResponseDto;

import java.util.List;

public interface CompanyReviewRepositoryExtension {
    List<CompanyReviewListResponseDto> ListCompanyReview(List<CompanyReview> companyReviews);
}
