package com.onz.modules.account.infra;

import com.onz.modules.account.domain.Account;
import com.onz.modules.account.web.dto.request.AccountSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountRepositoryExtension {

    Page<Account> accounts(AccountSearchRequest accountSearchRequest, Pageable pageable);

    Account deleteAccount(Long id);
}
