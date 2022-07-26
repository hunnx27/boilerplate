package com.onz.modules.counsel.infra.counselRecommand;


import com.onz.modules.counsel.domain.CounselRecommand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselRecommandRepository extends JpaRepository<CounselRecommand, Long>,
        CounselRecommandRepositoryExtension {

//    List<Counsel> findByParentCounselId(Long parentCounselId);
//    @Query("select c from Counsel c where c.qnaGubn='A' and c.parentCounsel.id = :parentCounselId")
//    List<Counsel> findByParentCounselId2(@Param("parentCounselId") Long parentCounselId);
}
