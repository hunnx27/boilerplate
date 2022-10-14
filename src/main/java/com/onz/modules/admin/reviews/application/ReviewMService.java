package com.onz.modules.admin.reviews.application;

import com.onz.common.exception.CustomException;
import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberRequestDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseWrapDto;
import com.onz.modules.admin.reviews.infra.ReviewMRepository;
import com.onz.modules.admin.reviews.web.dto.ReviewMRequestDto;
import com.onz.modules.admin.reviews.web.dto.ReviewMallResponseDto;
import com.onz.modules.admin.reviews.web.dto.ReviewsResponseDto;
import com.querydsl.jpa.JPQLQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewMService {
    private final ReviewMRepository reviewMRepository;

    public List<ReviewMallResponseDto> allReview(HttpServletResponse response, Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<ReviewMallResponseDto> liveMemberListResult = reviewMRepository.findByAllReview(pageable);
        return liveMemberListResult;
    }
    public List<ReviewsResponseDto> companyReview(HttpServletResponse response, Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<ReviewsResponseDto> liveMemberListResult = reviewMRepository.findByCompanyReview(pageable);
        return liveMemberListResult;
    }
    public List<ReviewsResponseDto> interviewReview(HttpServletResponse response, Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<ReviewsResponseDto> liveMemberListResult = reviewMRepository.findByInterviewReview(pageable);
        return liveMemberListResult;
    }
    public List<ReviewsResponseDto> amtReview(HttpServletResponse response, Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<ReviewsResponseDto> liveMemberListResult = reviewMRepository.findByAmtReview(pageable);
        return liveMemberListResult;
    }
}
