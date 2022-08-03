package com.onz.modules.company.application;

import com.onz.modules.company.domain.Company;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.onz.modules.company.infra.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Page<Company> list(CompanySearchRequest searchRequest) {
        return companyRepository.list(searchRequest);
    }

    public void create(Company company) {
        companyRepository.save(company);
    }

    public void update(CompanyUpdateRequest updateRequest) {
        companyRepository.update(updateRequest);
    }

    public Company findOne(Long id) {
        return companyRepository.findById(id)
            .orElseThrow();
    }
}
