package com.onz.modules.admin.member.deluser.application;

import com.onz.common.exception.CustomException;
import com.onz.modules.admin.member.deluser.infra.DelUserRepository;
import com.onz.modules.admin.member.deluser.infra.HumanRepository;
import com.onz.modules.admin.member.deluser.web.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class HumanService {

    private final HumanRepository humanRepository;

    public List<HumanListResponseDto> humanListResponseDtoList(HttpServletResponse response, HumanListRequestDto humanListRequestDto, Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<HumanListResponseDto> humanListResponseDtoList = humanRepository.findByHumanList(humanListRequestDto,pageable);

        return humanListResponseDtoList;
    }
    public HumanResponseDto humanAccountDetail(@PathVariable Long id) throws CustomException {
        //전체회원을 받아온다
        HumanResponseDto humanResponseDto = humanRepository.findByHumanDetail(id);

        return humanResponseDto;
    }

}
