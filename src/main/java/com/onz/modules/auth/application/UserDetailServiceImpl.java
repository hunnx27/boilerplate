package com.onz.modules.auth.application;

import com.onz.common.enums.ErrorCode;
import com.onz.common.exception.CustomException;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.auth.web.dto.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String nameOrEmail) throws CustomException {
        Account account = accountRepository.findByEmail(nameOrEmail);
        if (account == null) {
            account = accountRepository.findByName(nameOrEmail);
        }

        if (account == null) {
            //throw new UsernameNotFoundException(String.format("계정정보가 없습니다. %s",nameOrEmail));
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND, new String[]{nameOrEmail});
        }
        return UserPrincipal.to(account);
    }
}
