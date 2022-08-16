package com.onz.modules.company.web;

import com.onz.modules.account.domain.Account;
import com.onz.common.web.BaseApiController;
import com.onz.modules.company.application.CompanyService;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.web.dto.request.CompanyCreateRequest;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name="기관 제어",description = "기관을 제어하는 api.")
public class CompanyController extends BaseApiController {

    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Operation(summary = "기관 불러오기", description = "기관 레코드를 불러옵니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class)))
    })
    @GetMapping("/company")
    public Page<Company> list(CompanySearchRequest searchRequest) {
        return companyService.list(searchRequest);
    }

    @Operation(summary = "기관 생성하기", description = "기관 레코드를 생성합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "생성 완료", content = @Content(schema = @Schema(implementation = CompanyCreateRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanyCreateRequest.class)))
    })
    @PostMapping("/company")
    public void create(@RequestBody CompanyCreateRequest createRequest) {
        companyService.create(modelMapper.map(createRequest, Company.class));
    }

    @Operation(summary = "기관 수정하기", description = "기관 레코드를 수정합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 완료", content = @Content(schema = @Schema(implementation = CompanyUpdateRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanyUpdateRequest.class)))
    })
    @PatchMapping("/company/{id}")
    public void update(@PathVariable Long id,
        @RequestBody CompanyUpdateRequest updateRequest) {
        updateRequest.setId(id);
        companyService.update(updateRequest);
    }

    @Operation(summary = "단일 기관 불러오기", description = "단일 기관 레코드를 불러옵니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = PathVariable.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PathVariable.class)))
    })
    @GetMapping("/company/{id}")
    public Company findOne(@PathVariable Long id) {
        return companyService.findOne(id);
    }

    @Operation(summary = "기관에 사용자 등록하기", description = "등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = PathVariable.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PathVariable.class)))
    })
    @PostMapping("/company/{id}/add")
    public ResponseEntity addAccount(@PathVariable Long id, Account account) {
        Company one = companyService.findOne(id);
        one.addAccount(account);
        return ResponseEntity.ok().build();
    }
}
