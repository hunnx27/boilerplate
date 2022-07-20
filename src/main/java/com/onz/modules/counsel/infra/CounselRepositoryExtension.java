package com.onz.modules.counsel.infra;


import com.onz.modules.organization.domain.Organization;
import com.onz.modules.organization.web.dto.request.OrganizationSearchRequest;
import com.onz.modules.organization.web.dto.request.OrganizationUpdateRequest;
import org.springframework.data.domain.PageImpl;

public interface CounselRepositoryExtension {

    PageImpl<Organization> list(OrganizationSearchRequest organizationSearchRequest);

    void update(OrganizationUpdateRequest updateRequest);
}
