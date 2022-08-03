package com.onz.modules.review.web;

import com.onz.modules.account.domain.Account;
import com.onz.common.web.BaseApiController;
import com.onz.modules.company.application.CompanyService;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.web.dto.request.CompanyCreateRequest;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReviewController extends BaseApiController {

    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @GetMapping("/reviews")
    public Page<Company> list(CompanySearchRequest searchRequest) {
        return companyService.list(searchRequest);
    }

    @PostMapping("/reviews")
    public void create(@RequestBody CompanyCreateRequest createRequest) {
        companyService.create(modelMapper.map(createRequest, Company.class));
    }

    @PatchMapping("/reviews/{id}")
    public void update(@PathVariable Long id,
        @RequestBody CompanyUpdateRequest updateRequest) {
        updateRequest.setId(id);
        companyService.update(updateRequest);
    }

    @GetMapping("/reviews/{id}")
    public Company findOne(@PathVariable Long id) {
        return companyService.findOne(id);
    }

    @PostMapping("/reviews/{id}/add")
    public ResponseEntity addAccount(@PathVariable Long id, Account account) {
        Company one = companyService.findOne(id);
        one.addAccount(account);
        return ResponseEntity.ok().build();
    }
}
