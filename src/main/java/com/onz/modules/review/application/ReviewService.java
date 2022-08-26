package com.onz.modules.review.application;

import com.onz.modules.company.domain.Company;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.onz.modules.company.infra.CompanyRepository;
import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.embed.Review;
import com.onz.modules.review.infra.ReviewRepository;
import com.onz.modules.review.web.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;

    public Page<Company> list(CompanySearchRequest searchRequest) {
        return companyRepository.list(searchRequest);
    }
    public List<ReviewResponseDto> findByAllReview(Pageable pageable){
        return reviewRepository.findByAllReview(pageable);
    }

    public void create(Company company) {
        companyRepository.save(company);
    }

    public void update(CompanyUpdateRequest updateRequest) {
        companyRepository.update(updateRequest);
    }

    public Company findOne(Long id) {
        return companyRepository.findById(id)
            .orElseThrow();
    }
}
