package com.onz.modules.admin.reviews.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberDetailResponse;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseDto;
import com.onz.modules.admin.reviews.application.ReviewMService;
import com.onz.modules.admin.reviews.web.dto.ReviewMRequestDto;
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

@RestController
@RequiredArgsConstructor
@Tag(name = "어드민 제어", description = "어드민을 제어하는 api.")
public class ReviewMController {

    private final ReviewMService reviewMService;

    @Operation(summary = "리뷰 관리 ", description = "승인 대기중인 리뷰 관리입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class)))
    })
    @GetMapping("/admin/reviews")
    public ResponseEntity<ApiR<?>> allReview(HttpServletResponse response, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(reviewMService.allReview(pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "기관 리뷰 관리 ", description = "승인 대기중인 기관 리뷰 관리입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class)))
    })
    @GetMapping("/admin/reviews/company")
    public ResponseEntity<ApiR<?>> companyReview(HttpServletResponse response, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(reviewMService.companyReview(pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "인터뷰 리뷰 관리 ", description = "승인 대기중인 인터뷰 관리입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class)))
    })
    @GetMapping("/admin/reviews/interview")
    public ResponseEntity<ApiR<?>> interviewReview(HttpServletResponse response, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(reviewMService.interviewReview(pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "연봉 리뷰 관리 ", description = "승인 대기중인 연봉 리뷰 관리입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class)))
    })
    @GetMapping("/admin/reviews/amt")
    public ResponseEntity<ApiR<?>> amtReview(HttpServletResponse response, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(reviewMService.amtReview(pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }

    @Operation(summary = "리뷰 디테일 ", description = "리뷰 디테일입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = Account.class)))
    })
    @GetMapping("/admin/reviews/{id}")
    public ResponseEntity<ApiR<?>> review(HttpServletResponse response, @PathVariable Long id,String type) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(reviewMService.review(id,type)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "전체 기관 리뷰 관리 ", description = "승인 상태 관계없이 기관 리뷰 관리입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class)))
    })
    @GetMapping("/admin/reviews/all/company")
    public ResponseEntity<ApiR<?>> companyAllReview(HttpServletResponse response, ReviewMRequestDto reviewMRequestDto, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(reviewMService.companyAllReview(reviewMRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
}
