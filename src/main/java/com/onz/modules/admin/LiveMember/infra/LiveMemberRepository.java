package com.onz.modules.admin.LiveMember.infra;

import com.onz.common.enums.Gubn;
import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.LiveMember.web.dto.LiveMemberDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiveMemberRepository extends JpaRepository<Account, Long> ,LiveMemberRepositoryExtension{
    List<Account> findAllByGubn(Gubn gubn);

}
