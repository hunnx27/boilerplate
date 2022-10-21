package com.onz.modules.admin.counsels.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.admin.companies.web.dto.CompaniesRequestDto;
import com.onz.modules.admin.companies.web.dto.CompaniesResponseDto;
import com.onz.modules.admin.counsels.application.CounselsService;
import com.onz.modules.admin.counsels.infra.CounselsRepository;
import com.onz.modules.admin.counsels.web.dto.CounselsRequestDto;
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
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "어드민 제어", description = "어드민 제어관련 api")
public class CounselsController {

    private final CounselsService counselsService;
    private final CounselsRepository counselsRepository;

    @Operation(summary = "상담 검색  ", description = "상담 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/counsels")
    public ResponseEntity<ApiR<?>> counselsSearch(CounselsRequestDto counselsRequestDto,Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(counselsService.counselsSearch(counselsRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "상담 아이템 디테일  ", description = "상담 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/counsels/item")
    public ResponseEntity<ApiR<?>> counselsItem(CounselsRequestDto counselsRequestDto,String qnaItem,Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(counselsService.counselsItem(counselsRequestDto,qnaItem,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
}
