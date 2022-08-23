package com.onz.modules.review.web;

import com.onz.common.web.BaseApiController;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.review.application.AmtReviewService;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.web.dto.AmtRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name="연봉 리뷰 제어",description = "연봉 리뷰를 제어하는 api.")
public class AmtReviewController extends BaseApiController {

    private final AmtReviewService amtReviewService;

    @Operation(summary = "연봉 리뷰 등록", description = "연봉 리뷰를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 등록 완료", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @PostMapping("/review/amt")
    public void create(@AuthenticationPrincipal UserPrincipal me, AmtRequestDto amtRequestDto) {
        amtReviewService.create(amtRequestDto,me);
    }
}
