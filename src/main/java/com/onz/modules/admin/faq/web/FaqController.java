package com.onz.modules.admin.faq.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.admin.companies.web.dto.CompaniesResponseDto;
import com.onz.modules.admin.event.web.dto.EventCreateRequestDto;
import com.onz.modules.admin.event.web.dto.EventSearchRequestDto;
import com.onz.modules.admin.faq.application.FaqService;
import com.onz.modules.admin.faq.web.dto.FaqCreateRequestDto;
import com.onz.modules.admin.faq.web.dto.FaqSearchRequestDto;
import com.onz.modules.auth.web.dto.UserPrincipal;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@Tag(name = "어드민 FAQ 제어", description = "어드민 FAQ 제어관련 api")
public class FaqController {
    private final FaqService faqService;

    @Operation(summary = "FAQ 등록", description = "FAQ 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = EventCreateRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = EventCreateRequestDto.class)))
    })
    @PostMapping("/admin/faq")
    public void create(@AuthenticationPrincipal UserPrincipal me, FaqCreateRequestDto faqCreateRequestDto) {
        try {
            faqService.create(faqCreateRequestDto, me);
            ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccessWithNoContent());
        } catch (Exception e) {
            throw e;
        }
    }
    @Operation(summary = "FAQ 검색  ", description = "FAQ 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/faq")
    public ResponseEntity<ApiR<?>> faqSearch(FaqSearchRequestDto faqSearchRequestDto, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(faqService.faqSearch(faqSearchRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
}
