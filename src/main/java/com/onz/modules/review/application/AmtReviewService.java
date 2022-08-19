package com.onz.modules.review.application;

import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.infra.CompanyRepository;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.infra.AmtReviewRepository;
import com.onz.modules.review.web.dto.AmtRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AmtReviewService {

    private final AmtReviewRepository amtReviewRepository;
    private final CompanyRepository companyRepository;
    private final AccountService accountService;

    public void create(AmtRequestDto amtRequestDto, UserPrincipal me) {
        Long companyId = amtRequestDto.getCompanyId();
        Account account = accountService.findOne(me.getId());
        Company company = companyRepository.findById(companyId).orElse(null);
        YearAmtReview yearAmtReview = new YearAmtReview(amtRequestDto, company,account);
        amtReviewRepository.save(yearAmtReview);
    }

}