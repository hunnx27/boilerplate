package com.onz.modules.auth.application;

import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.UserAccount;
import com.onz.modules.account.infra.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String nameOrEmail) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(nameOrEmail);
        if (account == null) {
            account = accountRepository.findByName(nameOrEmail);
        }

        if (account == null) {
            throw new UsernameNotFoundException(nameOrEmail);
        }
        return UserAccount.to(account);
    }
}
