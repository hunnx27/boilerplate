package com.onz.modules.account.application;

import com.onz.modules.account.domain.embed.Myinfo;
import com.onz.modules.account.domain.enums.IntrsOrg;
import com.onz.modules.account.web.dto.AccountConverter;
import com.onz.modules.account.web.dto.request.AccountMyinfoUpdateRequest;
import com.onz.modules.account.web.dto.request.AccountSearchRequest;
import com.onz.modules.account.domain.enums.AuthProvider;
import com.onz.modules.account.domain.enums.Gubn;
import com.onz.modules.account.web.dto.request.AccountUpdateRequest;
import com.onz.common.enums.Role;
import com.onz.modules.auth.web.dto.request.SignupRequest;
import com.onz.modules.education.application.EducationService;
import com.onz.modules.education.domain.Education;
import com.onz.modules.education.infra.EducationRepository;
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
import java.util.Optional;

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

    public Account updateMyItem(Long id, AccountMyinfoUpdateRequest req){
        Optional<Account> accountOpt = accountRepository.findById(id);
        Account rsAccount = null;
        if(!accountOpt.isEmpty()){
            Account account = accountOpt.get();
            account.setUpdateMyinfo(req);
            rsAccount = accountRepository.save(account);
        }
        return rsAccount;
    }

    public List<Education> educations(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        return educationRepository.findEducationsByAccounts(account);
    }

    public Account getNewUser(SignupRequest signupRequest){
        Account user = Account.builder()
                .userId(signupRequest.getSocialId())
                .gubn(Gubn.of(signupRequest.getGubnCode()))
                .provider(AuthProvider.of(signupRequest.getSnsTypeCode()))
                .role(Role.USER)
                .build();
        accountRepository.save(user);
        return user;
    }

    public Account getMember(){
        Account account = new Account();
        Myinfo myinfo = new Myinfo();
        myinfo.setIntrsOrg(IntrsOrg.valueOf("ALL"));
        account.setMyinfo(myinfo);
        return null;
    }


}
