package com.onz.modules.counsel.infra.counsel;


import com.onz.modules.counsel.domain.Counsel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CounselRepositoryExtension {

    List<Counsel> findCounselList(Pageable pageable);
    List<Counsel> findAnswerList(Long parentCounselId, Pageable pageable);
    long countAdoptedAnswer(Long answerId, Long accountId);
}