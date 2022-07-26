package com.onz.modules.counsel.infra.CounselAnswerComment;


import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.CounselAnswerComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselAnswerCommentRepository extends JpaRepository<CounselAnswerComment, Long>,
        CounselAnswerCommentRepositoryExtension {

//    List<Counsel> findByParentCounselId(Long parentCounselId);
//    @Query("select c from Counsel c where c.qnaGubn='A' and c.parentCounsel.id = :parentCounselId")
//    List<Counsel> findByParentCounselId2(@Param("parentCounselId") Long parentCounselId);
}
