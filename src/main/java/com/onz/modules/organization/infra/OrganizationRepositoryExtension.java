package com.onz.modules.organization.infra;


import com.onz.modules.organization.application.request.OrganizationSearchRequest;
import com.onz.modules.organization.application.request.OrganizationUpdateRequest;
import com.onz.modules.organization.domain.Organization;
import org.springframework.data.domain.PageImpl;

public interface OrganizationRepositoryExtension {

    PageImpl<Organization> list(OrganizationSearchRequest organizationSearchRequest);

    void update(OrganizationUpdateRequest updateRequest);
}
