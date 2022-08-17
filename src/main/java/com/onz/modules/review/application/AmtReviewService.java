package com.onz.modules.review.application;

import com.onz.modules.company.domain.Company;
import com.onz.modules.company.infra.CompanyRepository;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.infra.AmtReviewRepository;
import com.onz.modules.review.web.dto.AmtRequestDto;
import com.sun.xml.bind.v2.schemagen.episode.SchemaBindings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AmtReviewService {

    private final AmtReviewRepository amtReviewRepository;
    private final CompanyRepository companyRepository;

    public void create(AmtRequestDto amtRequestDto) {
        Long companyId = amtRequestDto.getCompanyId();
        Company company = companyRepository.findById(companyId).orElse(null);
        YearAmtReview yearAmtReview = new YearAmtReview(amtRequestDto, company);
        amtReviewRepository.save(yearAmtReview);
    }

}