package com.onz.modules.review.web;

import com.onz.common.web.BaseApiController;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.review.application.InterviewService;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.web.dto.AmtRequestDto;
import com.onz.modules.review.web.dto.InterviewListResponseDto;
import com.onz.modules.review.web.dto.InterviewRequestDto;
import com.onz.modules.review.web.dto.YearAmtListResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name="인터뷰 리뷰 제어",description = "인터뷰 리뷰를 제어하는 api.")
public class InterviewReviewController extends BaseApiController {

    private final InterviewService interviewService;

    @Operation(summary = "인터뷰 리뷰 등록", description = "인터뷰 리뷰를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 등록 완료", content = @Content(schema = @Schema(implementation = InterviewRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = InterviewRequestDto.class)))
    })
    @PostMapping("/review/interview")
    public void create(@AuthenticationPrincipal UserPrincipal me, @RequestBody InterviewRequestDto interviewRequestDto) {
        interviewService.create(interviewRequestDto,me);
    }

    @Operation(summary = "인터뷰 리뷰 보기", description = "인터뷰 리뷰를 조회합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))
    })
    @GetMapping("/reviews/interview/{id}")
    public List<InterviewListResponseDto> interviewReviewList(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        return interviewService.interviewReviewList(pageable);
    }
}
