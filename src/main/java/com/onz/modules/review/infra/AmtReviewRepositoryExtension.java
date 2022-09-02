package com.onz.modules.review.infra;

import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.company.web.dto.reponse.YearAmtListResponseDto;

import java.util.List;

public interface AmtReviewRepositoryExtension {
    List<YearAmtListResponseDto> ListAmt(List<YearAmtReview> yearAmtReview);

}
