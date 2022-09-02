package com.onz.modules.company.web;

import com.onz.common.web.BaseApiController;
import com.onz.modules.company.application.CompanyService;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.web.dto.reponse.*;
import com.onz.modules.company.web.dto.request.AvgReqestDto;
import com.onz.modules.company.web.dto.request.CompanyCreateRequest;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.onz.modules.review.application.AmtReviewService;
import com.onz.modules.review.application.ReviewService;
import com.onz.modules.review.domain.YearAmtReview;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "기관 제어", description = "기관을 제어하는 api.")
public class CompanyController extends BaseApiController {

    private final CompanyService companyService;
    private final ModelMapper modelMapper;
    private final ReviewService reviewService;
    private final AmtReviewService amtReviewService;

    @Operation(summary = "기관 불러오기", description = "기관 레코드를 불러옵니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class)))
    })
    @GetMapping("/companies")
    public Page<Company> list(CompanySearchRequest searchRequest) {
        return companyService.list(searchRequest);
    }


    @Operation(summary = "기관이름으로 검색하기", description = "기관 이름으로 레코드를 불러옵니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class)))
    })
    @GetMapping("/companies/search/")
    public List<CompanySearchResponse> search(CompanySearchRequest companySearchRequest) {
        return companyService.search(companySearchRequest);
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

    @GetMapping("/companies/{id}")
    public CompanyDetailResponse findOne(@PathVariable Long id) {
        return companyService.findOne(id);
    }

    @Operation(summary = "단일 기관 지표 점수 불러오기", description = "단일 기관 지표점수를 불러옵니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = PathVariable.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PathVariable.class)))
    })
    @GetMapping("/companies/{id}/jipyo")
    public CompanyJipyoResponse findOneJipyo(@PathVariable Long id) {
        return companyService.findOneJipyo(id);
    }

    /*
    amt
     */
    @Operation(summary = "기관 평균 연봉 보기", description = "평균 연봉을 조회합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))
    })
    @GetMapping("/companies/{id}/review/amts/avg")
    public YearAmtAvgResponseDto amtReviewAvgList(@PathVariable Long id) {
        return amtReviewService.amtReviewAvgList(id);
    }

    @Operation(summary = "기관 연봉 리뷰 보기", description = "기관에 작성된 연봉 리뷰를 조회합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))
    })
    @GetMapping("/companies/{id}/review/amts")
    public List<YearAmtListResponseDto> companySearchAmt(@PathVariable Long id) {
        return reviewService.companySearchAmt(id);
    }// FIXME
    /*
    interview
     */

    @Operation(summary = "기관코드로 인터뷰 리뷰 보기", description = "기관에 작성된 인터뷰 리뷰를 조회합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))
    })
    @GetMapping("/companies/{id}/review/interviews")
    public List<InterviewListResponseDto> companySearchInterview(@PathVariable Long id) {
        return reviewService.companySearchInterview(id);
    }

    @Operation(summary = "기관코드로 인터뷰 리뷰 상단 api", description = "기관에 작성된 인터뷰를 계산합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))
    })
    @GetMapping("/companies/{id}/review/interviews/info")
    public InterviewcountResponsedto companyInterviewInfo(@PathVariable Long id) {
        return reviewService.companySearchInterviewCount(id);
    }

    /*
    companies
     */
    @Operation(summary = "기관코드로 기관 리뷰 보기", description = "기관에 작성된 기관 리뷰를 조회합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))
    })
    @GetMapping("/companies/{id}/review/companies")
    public List<CompanyReviewListResponseDto> companySearchCompany(@PathVariable Long id) {
        return reviewService.companySearchCompany(id);
    }
}