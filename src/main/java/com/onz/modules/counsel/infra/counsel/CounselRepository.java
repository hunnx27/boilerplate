package com.onz.modules.counsel.infra.counsel;


import com.onz.common.enums.Gubn;
import com.onz.modules.counsel.domain.Counsel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounselRepository extends JpaRepository<Counsel, Long>,
        CounselRepositoryExtension {

    long countByParentCounselId(Long parentCounselId);

//    List<Counsel> findByParentCounselId(Long parentCounselId);
//    @Query("select c from Counsel c where c.qnaGubn='A' and c.parentCounsel.id = :parentCounselId")
//    List<Counsel> findByParentCounselId2(@Param("parentCounselId") Long parentCounselId);
}
