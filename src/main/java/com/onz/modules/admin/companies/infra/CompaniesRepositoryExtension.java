package com.onz.modules.admin.companies.infra;

import com.onz.modules.admin.companies.web.dto.CompaniesRequestDto;
import com.onz.modules.admin.companies.web.dto.CompaniesResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompaniesRepositoryExtension {

    List<CompaniesResponseDto> findByCompanies(CompaniesRequestDto companiesRequestDto, Pageable pageable);
}
