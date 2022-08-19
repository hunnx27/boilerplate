package com.onz.modules.review.web;

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

import javax.servlet.http.HttpServletResponse;

/*
레거시 많아서 정리가 필요함
 */
@RequiredArgsConstructor
@RestController
@Tag(name="리뷰 제어",description = "리뷰를 제어하는 api.")
public class ReviewController extends BaseApiController {

    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Operation(summary = "리뷰 불러오기", description = "리뷰 레코드를 불러옵니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class)))
    })
    @GetMapping("/reviews")
    public Page<Company> list(CompanySearchRequest searchRequest) {
        return companyService.list(searchRequest);
    }

    @Operation(summary = "리뷰 등록", description = "리뷰를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 등록 완료", content = @Content(schema = @Schema(implementation = CompanyCreateRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanyCreateRequest.class)))
    })
    @PostMapping("/reviews")
    public void create(@RequestBody CompanyCreateRequest createRequest) {
        companyService.create(modelMapper.map(createRequest, Company.class));
    }

    @Operation(summary = "리뷰 수정", description = "리뷰를 수정합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 수정 완료", content = @Content(schema = @Schema(implementation = CompanyUpdateRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanyUpdateRequest.class)))
    })
    @PatchMapping("/reviews/{id}")
    public void update(@PathVariable Long id,
        @RequestBody CompanyUpdateRequest updateRequest) {
        updateRequest.setId(id);
        companyService.update(updateRequest);
    }

    @Operation(summary = "리뷰 단일 검색", description = "리뷰를 하나 검색합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "검색 완료", content = @Content(schema = @Schema(implementation = PathVariable.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = HttpServletResponse.class)))
    })
    @GetMapping("/reviews/{id}")
    public Company findOne(@PathVariable Long id) {
        return companyService.findOne(id);
    }

    @Operation(summary = "기업에 등록하기", description = "기업에 리뷰를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @PostMapping("/reviews/{id}/add")
    public ResponseEntity addAccount(@PathVariable Long id, Account account) {
//        Company one = companyService.findOne(id);
//        one.addAccount(account);
        return ResponseEntity.ok().build();
    }
}
