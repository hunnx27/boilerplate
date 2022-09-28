package com.onz.modules.admin.LiveMember.application;

import com.onz.common.exception.CustomException;
import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.LiveMember.domain.LiveMemberRequestDto;
import com.onz.modules.admin.LiveMember.domain.LiveMemberResponseDto;
import com.onz.modules.admin.LiveMember.infra.LiveMemberRepository;
import com.onz.modules.company.web.dto.reponse.YearAmtListResponseDto;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.counsel.infra.counsel.CounselRepository;
import com.onz.modules.review.infra.AmtReviewRepository;
import com.onz.modules.review.infra.CompanyReviewRepository;
import com.onz.modules.review.infra.InterviewReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LiveMemberService {

    private final LiveMemberRepository liveMemberRepository;
    private final CompanyReviewRepository companyReviewRepository;
    private final AmtReviewRepository amtReviewRepository;
    private final CounselRepository counselRepository;
    private final InterviewReviewRepository interviewReviewRepository;

    public List<LiveMemberResponseDto> liveMember(HttpServletResponse response, LiveMemberRequestDto liveMemberRequestDto) throws CustomException {
        //전체회원을 받아온다
        List<LiveMemberResponseDto> result = liveMemberRepository.findByLiveMember(liveMemberRequestDto);
//        List<LiveMemberResponseDto> result = liveMemberResponseDtos.stream().map(e -> {
//            LiveMemberResponseDto rs = new LiveMemberResponseDto(e);
//            // 기관리뷰 개수
//            Long companyReviewCnt = companyReviewRepository.countByAccount_Id(e.getId());
//            rs.setCompanyReviewCnt(companyReviewCnt);
//            // 인터뷰리뷰 개수
//            Long interviewReviewCnt = interviewReviewRepository.countByAccount_Id(e.getId());
//            rs.setInterviewReviewCnt(interviewReviewCnt);
//            // ...
//            // 연봉리뷰 개수
//            Long amtReviewCnt = amtReviewRepository.countByAccount_Id(e.getId());
//            rs.setAmtReviewCnt(amtReviewCnt);
//            // ...
//            // 상담리뷰 개수
//            Long counselQCnt = counselRepository.countByAccount_IdAndQnaGubn(e.getId(), QnaGubn.Q);
//            rs.setCounselQCnt(counselQCnt);
//            // ...
//            Long counselACnt = counselRepository.countByAccount_IdAndQnaGubn(e.getId(),QnaGubn.A);
//            rs.setCounselACnt(counselACnt);
//            return rs;
//        }).collect(Collectors.toList());
        return result;
    }
}
