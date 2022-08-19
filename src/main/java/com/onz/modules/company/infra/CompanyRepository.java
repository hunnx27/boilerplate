package com.onz.modules.company.infra;


import com.onz.modules.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long>,
        CompanyRepositoryExtension {
}
