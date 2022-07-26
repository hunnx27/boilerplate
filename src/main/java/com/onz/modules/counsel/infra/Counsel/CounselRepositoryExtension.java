package com.onz.modules.counsel.infra.Counsel;


import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.organization.domain.Organization;
import com.onz.modules.organization.web.dto.request.OrganizationSearchRequest;
import com.onz.modules.organization.web.dto.request.OrganizationUpdateRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CounselRepositoryExtension {
    List<Counsel> findAnswerList(Long parentCounselId, Pageable pageable);
}