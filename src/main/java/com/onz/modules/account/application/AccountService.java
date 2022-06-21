package com.onz.modules.account.application;

import com.onz.modules.account.application.request.AccountUpdateRequest;
import com.onz.common.enums.Role;
import com.onz.modules.education.application.EducationService;
import com.onz.modules.education.domain.Education;
import com.onz.modules.education.infra.EducationRepository;
import com.onz.modules.account.application.request.AccountSearchRequest;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.common.enums.YN;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;
    private final EducationRepository educationRepository;
    private final EducationService educationService;
    private final PasswordEncoder passwordEncoder;

    public Account create(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(Role.USER);
        return accountRepository.save(account);
    }

    public Page<Account> list(AccountSearchRequest accountSearchRequest, Pageable pageable) {
        return accountRepository.accounts(accountSearchRequest, pageable);
    }

    public Account findOne(Long id) {
        return accountRepository.findById(id)
            .orElseThrow(NoSuchElementException::new);
    }

    public Long update(AccountUpdateRequest account) {
        Account findOne = accountRepository.findById(account.getId())
            .orElseThrow(NoSuchElementException::new);
        findOne.setUpdateData(account);
        return accountRepository.save(findOne).getId();
    }

    public boolean delete(Long id) {
        Account account = accountRepository.deleteAccount(id);
        if (account.getIsDelete().equals(YN.Y)) {
            List<Education> educationsByAccounts = educationRepository.findEducationsByAccounts(
                account);
            educationsByAccounts.forEach(education -> education.removeAccount(account));
        }

        return true;
    }

    public List<Education> educations(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        return educationRepository.findEducationsByAccounts(account);
    }


}
