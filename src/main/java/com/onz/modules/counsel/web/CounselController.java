package com.onz.modules.counsel.web;

import com.onz.common.web.BaseApiController;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.application.CounselService;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.web.dto.request.CounselCreateRequest;
import com.onz.modules.organization.application.OrganizationService;
import com.onz.modules.organization.domain.Organization;
import com.onz.modules.organization.web.dto.request.OrganizationCreateRequest;
import com.onz.modules.organization.web.dto.request.OrganizationSearchRequest;
import com.onz.modules.organization.web.dto.request.OrganizationUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CounselController extends BaseApiController {

    private final CounselService counselService;
    private final ModelMapper modelMapper;

//    @GetMapping("/counsel")
//    public Page<Organization> list(OrganizationSearchRequest organizationSearchRequest) {
//        return organizationService.list(organizationSearchRequest);
//    }

    @PostMapping("/counsel")
    public void create(@RequestBody CounselCreateRequest counselCreateRequest) {
        counselService.create(modelMapper.map(counselCreateRequest, Counsel.class));
    }

//    @PatchMapping("/counsel/{id}")
//    public void update(@PathVariable Long id,
//        @RequestBody OrganizationUpdateRequest updateRequest) {
//        updateRequest.setId(id);
//        organizationService.update(updateRequest);
//    }

//    @GetMapping("/counsel/{id}")
//    public Organization findOne(@PathVariable Long id) {
//        return organizationService.findOne(id);
//    }

}
