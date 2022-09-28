package com.onz.modules.admin.LiveMember.infra;

import com.onz.common.enums.Gubn;
import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.LiveMember.domain.LiveMemberResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

public interface LiveMemberRepository extends JpaRepository<Account, Long> ,LiveMemberRepositoryExtension{
    List<Account> findAllByGubn(Gubn gubn);
}
