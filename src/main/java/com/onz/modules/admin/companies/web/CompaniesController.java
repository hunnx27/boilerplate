package com.onz.modules.admin.companies.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.companies.application.CompaniesService;
import com.onz.modules.admin.companies.web.dto.CompaniesDetailResponseDto;
import com.onz.modules.admin.companies.web.dto.CompaniesDetailReviewDto;
import com.onz.modules.admin.companies.web.dto.CompaniesRequestDto;
import com.onz.modules.admin.companies.web.dto.CompaniesResponseDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberDetailResponse;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberRequestDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@Tag(name = "어드민 제어", description = "어드민 제어관련 api")
public class CompaniesController {

    private final CompaniesService companiesService;

    @Operation(summary = "기관 검색 관리 ", description = "기관 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/companies")
    public ResponseEntity<ApiR<?>> companiesSearch(HttpServletResponse response, CompaniesRequestDto companiesRequestDto, Pageable pageable) {
//        liveMemberService.liveMember(response,liveMemberRequestDto);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companiesService.companiesSearch(response,companiesRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }

    @Operation(summary = "기관 디테일 ", description = "기관 디테일입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesDetailResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesDetailResponseDto.class)))
    })
    @GetMapping("/admin/companies/{id}")
    public ResponseEntity<ApiR<?>> companiesDetail(HttpServletResponse response, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companiesService.companiesDetail(response,id)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "기관-> 리뷰 ", description = "기관 디테일입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesDetailReviewDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesDetailReviewDto.class)))
    })
    @GetMapping("/admin/companies/review/{id}")
    public ResponseEntity<ApiR<?>> companiesDetailReview(HttpServletResponse response, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companiesService.companiesDetailReview(response,id)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "기관-> 지표 ", description = "기관 디테일입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = Account.class)))
    })
    @GetMapping("/admin/companies/jipyo/{id}")
    public ResponseEntity<ApiR<?>> companiesDetailJipyo(HttpServletResponse response, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companiesService.companiesDetailJipyo(response,id)));
        } catch (Exception e) {
            throw e;
        }
    }


}
