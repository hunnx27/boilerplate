package com.onz.modules.organization.application;

import com.onz.modules.organization.web.dto.request.OrganizationSearchRequest;
import com.onz.modules.organization.web.dto.request.OrganizationUpdateRequest;
import com.onz.modules.organization.domain.Organization;
import com.onz.modules.organization.infra.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Page<Organization> list(OrganizationSearchRequest organizationSearchRequest) {
        return organizationRepository.list(organizationSearchRequest);
    }

    public void create(Organization organization) {
        organizationRepository.save(organization);
    }

    public void update(OrganizationUpdateRequest updateRequest) {
        organizationRepository.update(updateRequest);
    }

    public Organization findOne(Long id) {
        return organizationRepository.findById(id)
            .orElseThrow();
    }
}
