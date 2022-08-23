package com.onz.modules.review.infra;

import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.YearAmtReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewReviewRepository  extends JpaRepository<InterviewReview, Long>,
        InterviewReviewRepositoryExtension{
}
