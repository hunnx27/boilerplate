package com.onz.modules.review.application;

import com.onz.common.util.dto.AttachDto;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.common.util.FileUtil;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.infra.CompanyRepository;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.infra.AmtReviewRepository;
import com.onz.modules.review.infra.CompanyReviewRepository;
import com.onz.modules.review.web.dto.AmtRequestDto;
import com.onz.modules.review.web.dto.CompanyReviewRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyReviewService {
    private final CompanyReviewRepository companyReviewRepository;
    private final CompanyRepository companyRepository;
    private final AccountService accountService;
    private final FileUtil fileUtil;

    public void create(CompanyReviewRequestDto companyReviewRequestDto, UserPrincipal me) {
        Long companyId = companyReviewRequestDto.getCompanyId();
        Account account = accountService.findOne(me.getId());
        Company company = companyRepository.findById(companyId).orElse(null);
        CompanyReview companyReview = new CompanyReview(companyReviewRequestDto, company, account);
        CompanyReview saved = companyReviewRepository.save(companyReview);
        // Image File Upload
        if (companyReviewRequestDto.getFiles() != null && companyReviewRequestDto.getFiles().size() > 0) {
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(companyReviewRequestDto.getFiles(), saved.getId());
                saved.setImages(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
