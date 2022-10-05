package com.onz.modules.admin.member.deluser.application;

import com.onz.common.exception.CustomException;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.admin.member.deluser.infra.DelUserRepository;
import com.onz.modules.admin.member.deluser.web.dto.DelUserListResponseDto;
import com.onz.modules.admin.member.deluser.web.dto.DelUserRequestDto;
import com.onz.modules.admin.member.deluser.web.dto.DelUserResponse;
import com.onz.modules.admin.member.livemember.infra.LiveMemberRepository;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberRequestDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseWrapDto;
import com.querydsl.jpa.JPQLQuery;
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
public class DelUserService {

    private final DelUserRepository delUserRepository;

    public List<DelUserListResponseDto> delUserList(HttpServletResponse response, DelUserRequestDto delUserRequestDto, Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<DelUserListResponseDto> delUserListResponse = delUserRepository.findByDelUser(delUserRequestDto,pageable);

        return delUserListResponse;
    }

    public DelUserResponse delUserDetail(@PathVariable Long id) throws CustomException {
        //전체회원을 받아온다
        DelUserResponse delUserListResponse = delUserRepository.findByDelUserDetail(id);

        return delUserListResponse;
    }

}
