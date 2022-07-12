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
    public UserDetails loadUserByUsername(String encodedUserId) throws CustomException {
//        Account account = accountRepository.findByPlainUserId(userId, AuthProvider.local).get();
        Account account = accountRepository.findByEncodedUserId2(encodedUserId).get();

        if (account == null) {
            //throw new UsernameNotFoundException(String.format("계정정보가 없습니다. %s",nameOrEmail));
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND, new String[]{encodedUserId});
        }
        return UserPrincipal.create(account);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND, new String[]{String.valueOf(id)}));

        return UserPrincipal.create(account);
    }
}
