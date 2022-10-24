package com.onz.modules.admin.counsels.infra;

import com.onz.modules.admin.counsels.web.dto.*;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CounselsRepositoryExtension {
    List<CounselsResponseDto> findcounselsSearch (CounselsRequestDto counselsRequestDto, Pageable pageable);
    List<CounselsResponseDto> findcounselsItemSearch (CounselsRequestDto counselsRequestDto, Pageable pageable,String qnaItem);
    CountEvent findcount(CounselsRequestDto counselsRequestDto);
    List<CounselAnswerListResponseDto> findByAnswer(Long answerId);
}
