package com.onz.modules.account.infra;

import com.onz.modules.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>,
    AccountRepositoryExtension {

//    Optional<Account> findByUserId(String userId);

}
