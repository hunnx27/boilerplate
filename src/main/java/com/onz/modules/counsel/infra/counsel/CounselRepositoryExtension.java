package com.onz.modules.counsel.infra.counsel;


import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.web.dto.request.counsel.CounselSearchRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CounselRepositoryExtension {

    List<Counsel> findCounselList(CounselSearchRequest counselSearchRequest, Pageable pageable);
    List<Counsel> findAnswerList(Long parentCounselId, Pageable pageable);
    long countAdoptedAnswer(Long answerId, Long accountId);
}