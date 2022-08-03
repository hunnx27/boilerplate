package com.onz.modules.company.infra;


import com.onz.modules.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>,
        CompanyRepositoryExtension {

}
