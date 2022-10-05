package com.onz.modules.admin.member.livemember.application;

import com.onz.common.exception.CustomException;
import com.onz.modules.admin.member.livemember.infra.LiveMemberRepository;
import com.onz.modules.admin.member.livemember.web.dto.*;
import com.onz.modules.counsel.infra.counsel.CounselRepository;
import com.onz.modules.review.infra.AmtReviewRepository;
import com.onz.modules.review.infra.CompanyReviewRepository;
import com.onz.modules.review.infra.InterviewReviewRepository;
import com.querydsl.jpa.JPQLQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LiveMemberService {

    private final LiveMemberRepository liveMemberRepository;

    public LiveMemberResponseWrapDto liveMember(HttpServletResponse response, LiveMemberRequestDto liveMemberRequestDto, Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<LiveMemberResponseDto> liveMemberListResult = liveMemberRepository.findByLiveMember(liveMemberRequestDto,pageable);
        LiveMemberResponseDto liveMemberTotalResult = liveMemberRepository.findByLiveMemberTotal(liveMemberRequestDto);
        JPQLQuery<Long> liveMemberTotalCnt = liveMemberRepository.findCountMember(liveMemberRequestDto);

        LiveMemberResponseWrapDto result3 = new LiveMemberResponseWrapDto(liveMemberTotalCnt.fetchOne(),liveMemberTotalResult, liveMemberListResult);
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
        return result3;
    }
    public LiveMemberDetailResponse liveMemberDetail(HttpServletResponse response, @PathVariable Long id){
        return liveMemberRepository.findByAccountDetail(id);
    }
    public LiveMemberResponseWrapPDto liveMemberResponseWrapPDto(HttpServletResponse response, @PathVariable Long id, Pageable pageable) {
        List<LiveMemberPointListResponse> liveMemberPointListResponses = liveMemberRepository.findByAccountPointList(id,pageable);
        LiveMemberPointResponse liveMemberPointResponse = liveMemberRepository.findByAccountPointDetail(id);
//
        LiveMemberResponseWrapPDto result3 = new LiveMemberResponseWrapPDto(liveMemberPointResponse,liveMemberPointListResponses);
//
       return result3;
    }


}