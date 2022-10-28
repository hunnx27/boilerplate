package com.onz.modules.follower.application;

import com.onz.common.web.ApiR;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.infra.CompanyRepository;
import com.onz.modules.follower.domain.Follower;
import com.onz.modules.follower.infra.FollowerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FollowerService {
    private final FollowerRepository followerRepository;
    private final AccountRepository accountRepository;
    private final CompanyRepository companyRepository;

    public ApiR<?> create(UserPrincipal me, Long id) {
        Account account = accountRepository.findById(me.getId()).orElse(null);
        Company company = companyRepository.findById(id).orElse(null);
        Follower follower = followerRepository.findById(id).orElse(null);
        Follower check = followerRepository.findByCompanyIdAndAccount_Id(id,me.getId());
        if (check != null) {
            if (!check.getAccount().getId().equals(me.getId())) {
                if (account != null && company != null) {
                    if (follower == null) {
                        follower = Follower.builder()
                                .createdAt(ZonedDateTime.now())
                                .gubnCode(company.getInterestCompany())
                                .account(account)
                                .company(company)
                                .build();
                        followerRepository.save(follower);
                        return ApiR.createSuccess(HttpStatus.OK);
                    } else {
                        return null;
                    }
                }
            }
        } else {
            if (account != null && company != null) {
                if (follower == null) {
                    follower = Follower.builder()
                            .createdAt(ZonedDateTime.now())
                            .gubnCode(company.getInterestCompany())
                            .account(account)
                            .company(company)
                            .build();
                    followerRepository.save(follower);
                    return ApiR.createSuccess(HttpStatus.OK);
                } else {
                    return null;
                }
            }
        }
        return ApiR.createError(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
