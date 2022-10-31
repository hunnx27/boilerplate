package com.onz.modules.admin.faq.application;


import com.onz.common.web.ApiR;
import com.onz.common.web.dto.response.enums.Role;
import com.onz.modules.admin.faq.domian.Faq;
import com.onz.modules.admin.faq.infra.FaqRepository;
import com.onz.modules.admin.faq.web.dto.FaqCreateRequestDto;
import com.onz.modules.admin.faq.web.dto.FaqSearchDetailResponseDto;
import com.onz.modules.admin.faq.web.dto.FaqSearchRequestDto;
import com.onz.modules.admin.faq.web.dto.FaqSearchResponseDto;
import com.onz.modules.admin.notice.domain.Notice;
import com.onz.modules.admin.notice.web.dto.NoticeSearchDetailResponseDto;
import com.onz.modules.auth.web.dto.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FaqService {
    private final FaqRepository faqRepository;
    public ResponseEntity<ApiR<?>> create(FaqCreateRequestDto faqCreateRequestDto, UserPrincipal me) {
        Faq faq = new Faq(faqCreateRequestDto);
        faq.setUserId(me.getUserId());
        faq.setCreateDt(ZonedDateTime.now());
        faqRepository.save(faq);
        return null;
    }
    public List<FaqSearchResponseDto> faqSearch(FaqSearchRequestDto faqSearchRequestDto, Pageable pageable) {
        List<FaqSearchResponseDto> list = faqRepository.findFaqSearch(faqSearchRequestDto, pageable);
        return list;
    }

    public FaqSearchDetailResponseDto faqSearchDetail(Long id, UserPrincipal me) {
        Optional<Faq> result = faqRepository.findById(id);
        FaqSearchDetailResponseDto faqSearchDetailResponseDto = new FaqSearchDetailResponseDto(result);
            return faqSearchDetailResponseDto;
    }
}
