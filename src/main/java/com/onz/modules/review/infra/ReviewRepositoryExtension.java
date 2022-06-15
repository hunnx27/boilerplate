package com.onz.modules.review.infra;


import com.onz.modules.organization.application.request.OrganizationSearchRequest;
import com.onz.modules.organization.application.request.OrganizationUpdateRequest;
import com.onz.modules.organization.domain.Organization;
import org.springframework.data.domain.PageImpl;

public interface ReviewRepositoryExtension {

    PageImpl<Organization> list(OrganizationSearchRequest organizationSearchRequest);

    void update(OrganizationUpdateRequest updateRequest);
}
