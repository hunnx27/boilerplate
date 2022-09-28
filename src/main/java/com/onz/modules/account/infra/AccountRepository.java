package com.onz.modules.account.infra;

import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.LiveMember.domain.LiveMemberResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>,
        AccountRepositoryExtension {

    //    Optional<Account> findByUserId(String userId);
    boolean existsByUserId(String userId);
    boolean existsAccountByUserId(String userId);


}
