package com.onz.modules.review.infra;

import com.onz.modules.review.domain.YearAmtReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmtReviewRepository extends JpaRepository<YearAmtReview, Long>,
        AmtReviewRepositoryExtension{
}
