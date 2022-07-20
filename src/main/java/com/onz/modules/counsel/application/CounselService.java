package com.onz.modules.counsel.application;

import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.infra.CounselRepository;
import com.onz.modules.organization.domain.Organization;
import com.onz.modules.organization.infra.OrganizationRepository;
import com.onz.modules.organization.web.dto.request.OrganizationSearchRequest;
import com.onz.modules.organization.web.dto.request.OrganizationUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CounselService {

    private final CounselRepository counselRepository;

//    public Page<Organization> list(OrganizationSearchRequest organizationSearchRequest) {
//        return CounselService.this.counselRepository.list(organizationSearchRequest);
//    }

    public void create(Counsel counsel) {
        CounselService.this.counselRepository.save(counsel);
    }

//    public void update(OrganizationUpdateRequest updateRequest) {
//        CounselService.this.counselRepository.update(updateRequest);
//    }
//
//    public Organization findOne(Long id) {
//        return CounselService.this.counselRepository.findById(id)
//            .orElseThrow();
//    }
}
