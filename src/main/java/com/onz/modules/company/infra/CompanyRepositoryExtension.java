package com.onz.modules.company.infra;


import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.onz.modules.company.domain.Company;
import org.springframework.data.domain.PageImpl;

public interface CompanyRepositoryExtension {

    PageImpl<Company> list(CompanySearchRequest companySearchRequest);

    void update(CompanyUpdateRequest updateRequest);
}
