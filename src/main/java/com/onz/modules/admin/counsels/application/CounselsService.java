package com.onz.modules.admin.counsels.application;

import com.onz.common.exception.CustomException;
import com.onz.modules.admin.counsels.infra.CounselsRepository;
import com.onz.modules.admin.counsels.web.dto.CounselsRequestDto;
import com.onz.modules.admin.counsels.web.dto.CounselsResponseDto;
import com.onz.modules.admin.counsels.web.dto.CounselsWrapResponseDto;
import com.onz.modules.admin.counsels.web.dto.CountEvent;
import com.querydsl.jpa.JPQLQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CounselsService {
    private final CounselsRepository counselsRepository;

    public CounselsWrapResponseDto counselsSearch(CounselsRequestDto counselsRequestDto, Pageable pageable) throws CustomException {
        // 모든 리뷰 조회하는데 state가 W인 항목만
        List<CounselsResponseDto> Result = counselsRepository.findcounselsSearch(counselsRequestDto, pageable);
        CountEvent countEvent= eventCount(counselsRequestDto);
        CounselsWrapResponseDto counselsWrapResponseDto = new CounselsWrapResponseDto(countEvent,Result);
        return counselsWrapResponseDto;
    }

    public CountEvent eventCount(CounselsRequestDto counselsRequestDto) {
        CountEvent countEvent = counselsRepository.findcount(counselsRequestDto);
        return countEvent;
    }

    public List<CounselsResponseDto> counselsItem(CounselsRequestDto counselsRequestDto,String qnaItem,Pageable pageable) {
       List<CounselsResponseDto> list = counselsRepository.findcounselsItemSearch(counselsRequestDto,pageable,qnaItem);
        return list;
    }
//    public List<CounselsResponseDto> Result(String type,CounselsResponseDto counselsResponseDto){
//
//    }
}
