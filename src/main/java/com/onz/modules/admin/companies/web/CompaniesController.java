package com.onz.modules.admin.companies.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.companies.application.CompaniesService;
import com.onz.modules.admin.companies.domain.Companies;
import com.onz.modules.admin.companies.web.dto.*;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberDetailResponse;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberRequestDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseDto;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.review.web.dto.AmtRequestDto;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiR<?>> companiesSearch(CompaniesRequestDto companiesRequestDto, Pageable pageable) {
//        liveMemberService.liveMember(response,liveMemberRequestDto);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companiesService.companiesSearch(companiesRequestDto,pageable)));
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
    public ResponseEntity<ApiR<?>> companiesDetail(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companiesService.companiesDetail(id)));
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
    public ResponseEntity<ApiR<?>> companiesDetailReview(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companiesService.companiesDetailReview(id)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "기관-> 지표 ", description = "기관 디테일입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesDetailJipyoDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesDetailJipyoDto.class)))
    })
    @GetMapping("/admin/companies/jipyo/{id}")
    public ResponseEntity<ApiR<?>> companiesDetailJipyo(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companiesService.companiesDetailJipyo(id)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "기관 정보수정요청 목록 ", description = "기관 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/companies/fix")
    public ResponseEntity<ApiR<?>> companiesSearch(CompaniesFixRequestDto companiesFixRequestDto, Pageable pageable) {
//        liveMemberService.liveMember(response,liveMemberRequestDto);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companiesService.companiesFixSearch(companiesFixRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }

    @Operation(summary = "기관 정보수정요청 단일조회 ", description = "기관 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/companies/fix/{id}")
    public ResponseEntity<ApiR<?>> companiesFixDetail(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companiesService.companiesFixDetail(id)));
        } catch (CustomException e) {
            throw e;
        }
    }

    @Operation(summary = "기업 정보 수정요청", description = "요청을 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @PostMapping("/help/companies/fix")
    public ResponseEntity<?> CompanyFix(@AuthenticationPrincipal UserPrincipal me, @RequestBody @Validated CompaniesFixCreateRequestDto companiesFixCreateRequestDto) {
        return ResponseEntity.ok().body(companiesService.CompanyFix(companiesFixCreateRequestDto, me));
    }

    @Operation(summary = "기업 정보 수정요청 승인", description = "수정합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @PutMapping("/admin/companies/fix/{id}")
    public void CompanyFixUpdate(@AuthenticationPrincipal UserPrincipal me,@RequestBody CompaniesFixUpdateRequestDto companiesFixUpdateRequestDto, @PathVariable Long id) {
        try {
            companiesService.CompanyFixUpdate(companiesFixUpdateRequestDto, me, id);
        } catch (Exception e) {
            throw e;
        }
    }
    @Operation(summary = "기업 추가 요청", description = "요청을 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @PostMapping("/help/companies/request")
    public ResponseEntity<?> CompanyAdd(@AuthenticationPrincipal UserPrincipal me, @RequestBody @Validated CompaniesCreateRequestDto companiesCreateRequestDto) {
        return ResponseEntity.ok(companiesService.CompanyAdd(companiesCreateRequestDto,me));
    }
    @Operation(summary = "기업 추가 요청 승인", description = "수정합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @PutMapping("/admin/companies/request/{id}")
    public void CompanyAddUpdate(@AuthenticationPrincipal UserPrincipal me,@RequestBody CompaniesFixUpdateRequestDto companiesFixUpdateRequestDto, @PathVariable Long id) {
        try {
            companiesService.CompanyAddUpdate(companiesFixUpdateRequestDto, me, id);
        } catch (Exception e) {
            throw e;
        }
    }
}
