package com.onz.modules.organization.web;

import com.onz.modules.account.domain.Account;
import com.onz.modules.common.web.BaseApiController;
import com.onz.modules.organization.application.OrganizationService;
import com.onz.modules.organization.application.request.OrganizationCreateRequest;
import com.onz.modules.organization.application.request.OrganizationSearchRequest;
import com.onz.modules.organization.application.request.OrganizationUpdateRequest;
import com.onz.modules.organization.domain.Organization;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrganizationController extends BaseApiController {

    private final OrganizationService organizationService;
    private final ModelMapper modelMapper;

    @GetMapping("/organizations")
    public Page<Organization> list(OrganizationSearchRequest organizationSearchRequest) {
        return organizationService.list(organizationSearchRequest);
    }

    @PostMapping("/organizations")
    public void create(@RequestBody OrganizationCreateRequest organizationCreateRequest) {
        organizationService.create(modelMapper.map(organizationCreateRequest, Organization.class));
    }

    @PatchMapping("/organizations/{id}")
    public void update(@PathVariable Long id,
        @RequestBody OrganizationUpdateRequest updateRequest) {
        updateRequest.setId(id);
        organizationService.update(updateRequest);
    }

    @GetMapping("/organizations/{id}")
    public Organization findOne(@PathVariable Long id) {
        return organizationService.findOne(id);
    }

    @PostMapping("/organizations/{id}/add")
    public ResponseEntity addAccount(@PathVariable Long id, Account account) {
        Organization one = organizationService.findOne(id);
        one.addAccount(account);
        return ResponseEntity.ok().build();
    }
}
