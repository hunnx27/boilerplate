package com.onz.modules.account.infra;

import com.onz.modules.account.domain.Account;
import com.onz.modules.company.web.dto.reponse.YearAmtListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long>,
        AccountRepositoryExtension {

    //    Optional<Account> findByUserId(String userId);

}
