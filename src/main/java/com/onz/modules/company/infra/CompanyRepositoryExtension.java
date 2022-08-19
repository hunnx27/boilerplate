package com.onz.modules.company.infra;


import com.onz.modules.company.web.dto.reponse.CompanySearchResponse;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.onz.modules.company.domain.Company;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

public interface CompanyRepositoryExtension {

    PageImpl<Company> list(CompanySearchRequest companySearchRequest);
    List<CompanySearchResponse> search(CompanySearchRequest companySearchRequest);
    void update(CompanyUpdateRequest updateRequest);
}
