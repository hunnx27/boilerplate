package com.onz.modules.education.infra;


import com.onz.modules.account.domain.Account;
import com.onz.modules.education.domain.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long>,
    EducationRepositoryExtension {

    Education findByName(String name);

    List<Education> findEducationsByAccounts(Account account);
}
