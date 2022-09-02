package com.onz.modules.review.infra;

import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.company.web.dto.reponse.CompanyReviewListResponseDto;

import java.util.List;

public interface CompanyReviewRepositoryExtension {
    List<CompanyReviewListResponseDto> listCompanyReview(List<CompanyReview> companyReviews);
    List<CompanyReview> listCompanyReviewByCompanyId(Long companyId);
}
