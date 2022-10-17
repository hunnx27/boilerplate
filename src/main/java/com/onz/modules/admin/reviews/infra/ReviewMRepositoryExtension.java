package com.onz.modules.admin.reviews.infra;

import com.onz.modules.admin.reviews.web.dto.ReviewMallResponseDto;
import com.onz.modules.admin.reviews.web.dto.ReviewsResponseDto;
import com.onz.modules.review.domain.dto.ReviewAllDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewMRepositoryExtension {
    List<ReviewMallResponseDto> findByAllReview(Pageable pageable);
    List<ReviewsResponseDto> findByCompanyReview(Pageable pageable);
    List<ReviewsResponseDto> findByInterviewReview(Pageable pageable);
    List<ReviewsResponseDto> findByAmtReview(Pageable pageable);
}
