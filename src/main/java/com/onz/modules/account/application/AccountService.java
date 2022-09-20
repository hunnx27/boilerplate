package com.onz.modules.account.application;

import com.onz.common.enums.InterestCompany;
import com.onz.modules.account.domain.embed.Myinfo;
import com.onz.modules.account.web.dto.AccountConverter;
import com.onz.modules.account.web.dto.request.AccountMyinfoUpdateRequest;
import com.onz.modules.account.web.dto.request.AccountSearchRequest;
import com.onz.modules.account.domain.enums.AuthProvider;
import com.onz.common.enums.Gubn;
import com.onz.modules.account.web.dto.request.AccountUpdateRequest;
import com.onz.common.enums.Role;
import com.onz.modules.auth.web.dto.request.SignupRequest;
import com.onz.modules.common.pointHistory.domain.PointHistory;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import com.onz.modules.common.pointHistory.infra.PointHistoryRepository;
import com.onz.modules.common.pointHistory.web.dto.response.PointHistoryResponse;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.common.enums.YN;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;
    private final PasswordEncoder passwordEncoder;
    private final PointHistoryRepository pointHistoryRepository;

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

    public Account deleteMeSoft(Long id){
        Account account = accountRepository.findById(id).orElseThrow();
        account.setIsDelete(YN.Y);
        accountRepository.save(account);
        return account;
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
        myinfo.setInterestCompany(InterestCompany.valueOf("ALL"));
        account.setMyinfo(myinfo);
        return null;
    }

    public void createMyPointHistories(Account account, PointTable pointTable){
//        Account account = accountRepository.findById(id).orElseGet(null);
//        if(account != null){
            pointHistoryRepository.save(new PointHistory(account, pointTable));
//        }
    }

    // FIXME Pageable 추가 필수
    // 이력 조회
    public Page<PointHistoryResponse> getMyPointHistories(Long accountId) {
        Pageable pageable = Pageable.ofSize(1000).first();
        Page<PointHistory> pageList = pointHistoryRepository.findByAccountId(accountId, pageable);
        List<PointHistoryResponse> rs = pageList.get().map(PointHistoryResponse::new).collect(Collectors.toList());
        return new PageImpl<>(rs);
    }


}
