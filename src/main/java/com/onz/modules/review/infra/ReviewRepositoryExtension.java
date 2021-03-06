package com.onz.modules.review.infra;


import com.onz.modules.organization.web.dto.request.OrganizationSearchRequest;
import com.onz.modules.organization.web.dto.request.OrganizationUpdateRequest;
import com.onz.modules.organization.domain.Organization;
import org.springframework.data.domain.PageImpl;

public interface ReviewRepositoryExtension {

    PageImpl<Organization> list(OrganizationSearchRequest organizationSearchRequest);

    void update(OrganizationUpdateRequest updateRequest);
}
