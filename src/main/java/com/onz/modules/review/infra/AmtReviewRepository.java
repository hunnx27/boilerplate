package com.onz.modules.review.infra;

import com.onz.modules.company.domain.Company;
import com.onz.modules.review.domain.YearAmtReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmtReviewRepository extends JpaRepository<YearAmtReview, Long>,
        AmtReviewRepositoryExtension{
}
