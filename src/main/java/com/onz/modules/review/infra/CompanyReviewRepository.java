package com.onz.modules.review.infra;

import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.domain.InterviewReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyReviewRepository extends JpaRepository<CompanyReview, Long>,
        InterviewReviewRepositoryExtension{
}
