package com.onz.modules.admin.reviews.application;

import com.onz.common.exception.CustomException;
import com.onz.modules.admin.reviews.infra.ReviewMRepository;
import com.onz.modules.admin.reviews.web.dto.*;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.domain.dto.ReviewAllDto;
import com.onz.modules.review.domain.embed.Review;
import com.onz.modules.review.infra.AmtReviewRepository;
import com.onz.modules.review.infra.CompanyReviewRepository;
import com.onz.modules.review.infra.InterviewReviewItemRepository;
import com.onz.modules.review.infra.InterviewReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewMService {
    private final ReviewMRepository reviewMRepository;
    private final AmtReviewRepository amtReviewRepository;
    private final InterviewReviewRepository interviewReviewRepository;
    private final CompanyReviewRepository companyReviewRepository;

    public List<ReviewMallResponseDto> allReview(Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<ReviewMallResponseDto> Result = reviewMRepository.findByAllReview(pageable);
        return Result;
    }

    public List<ReviewsResponseDto> companyReview(Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<ReviewsResponseDto> Result = reviewMRepository.findByCompanyReview(pageable);
        return Result;
    }

    public List<ReviewsResponseDto> interviewReview(Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<ReviewsResponseDto> Result = reviewMRepository.findByInterviewReview(pageable);
        return Result;
    }

    public List<ReviewsResponseDto> amtReview(Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<ReviewsResponseDto> Result = reviewMRepository.findByAmtReview(pageable);
        return Result;
    }

    public AllReviewResponsedto review(Long reviewId, String type) {
        switch (type) {
            case "AMT":
                YearAmtReview amtReview = amtReviewRepository.findById(reviewId).orElse(null);
                AmtReviewResponseDto amtReviewResponseDto = new AmtReviewResponseDto(amtReview);
                AllReviewResponsedto allReviewResponsedto = new AllReviewResponsedto(amtReviewResponseDto);
                return allReviewResponsedto;
            case "INTERVIEW":
                InterviewReview interviewReview = interviewReviewRepository.findById(reviewId).orElse(null);
                InterviewReviewResponseDto interviewReviewResponseDto = new InterviewReviewResponseDto(interviewReview);
                AllReviewResponsedto allReviewResponsedto1 = new AllReviewResponsedto(interviewReviewResponseDto);
                return allReviewResponsedto1;
            case "COMPANY":
                CompanyReview companyReview = companyReviewRepository.findById(reviewId).orElse(null);
                CompanyReviewResponseDto companyReviewResponseDto = new CompanyReviewResponseDto(companyReview);
                AllReviewResponsedto allReviewResponsedto2 = new AllReviewResponsedto(companyReviewResponseDto);
                return allReviewResponsedto2;
            default:
                log.info("hi!");
                break;
        }

        return null;
    }
}
