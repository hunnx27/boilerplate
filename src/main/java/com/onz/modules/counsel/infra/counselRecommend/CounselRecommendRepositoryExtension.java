package com.onz.modules.counsel.infra.counselRecommend;


import com.onz.modules.counsel.domain.CounselRecommend;

import java.util.List;

public interface CounselRecommendRepositoryExtension {
//    List<Counsel> findAnswerList(Long parentCounselId, Pageable pageable);

    List<CounselRecommend> findRecommend(Long answerId);
    long countNotice(Long answerId, Long accountId);
}