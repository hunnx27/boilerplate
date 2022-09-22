package com.onz.modules.company.web;

import com.onz.common.web.ApiR;
import com.onz.common.web.BaseApiController;
import com.onz.modules.company.application.CompanyService;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.web.dto.reponse.*;
import com.onz.modules.company.web.dto.request.CompanyCreateRequest;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.onz.modules.review.application.AmtReviewService;
import com.onz.modules.review.application.CompanyReviewService;
import com.onz.modules.review.application.InterviewService;
import com.onz.modules.review.application.ReviewService;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.web.dto.CompanyReviewDetailResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "기관 제어", description = "기관을 제어하는 api.")
public class CompanyController extends BaseApiController {

    private final CompanyService companyService;
    private final ModelMapper modelMapper;
    private final ReviewService reviewService;
    private final InterviewService interviewService;
    private final CompanyReviewService companyReviewService;
    private final AmtReviewService amtReviewService;

    /**
     * Company API
     */
    @Operation(summary = "기관 불러오기", description = "기관 레코드를 불러옵니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class)))})
    @GetMapping("/companies")
    public ResponseEntity<ApiR<?>> list(CompanySearchRequest searchRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companyService.list(searchRequest)));
        } catch (Exception e) {
            throw e;
        }
    }


    @Operation(summary = "기관이름으로 검색하기", description = "기관 이름으로 레코드를 불러옵니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class)))})
    @GetMapping("/companies/search")
    public ResponseEntity<ApiR<Page<CompanySearchResponse>>> search(CompanySearchRequest companySearchRequest, @PageableDefault(size = 20, sort = "officeName", direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companyService.search(companySearchRequest, pageable)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "기관 생성하기", description = "기관 레코드를 생성합니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "생성 완료", content = @Content(schema = @Schema(implementation = CompanyCreateRequest.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanyCreateRequest.class)))})
    @PostMapping("/company")
    public void create(@RequestBody CompanyCreateRequest createRequest) {
        try {
            companyService.create(modelMapper.map(createRequest, Company.class));
            ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccessWithNoContent());
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "기관 수정하기", description = "기관 레코드를 수정합니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "수정 완료", content = @Content(schema = @Schema(implementation = CompanyUpdateRequest.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanyUpdateRequest.class)))})
    @PatchMapping("/company/{id}")
    public void update(@PathVariable Long id, @RequestBody CompanyUpdateRequest updateRequest) {
        try {
            updateRequest.setId(id);
            companyService.update(updateRequest);
            ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccessWithNoContent());
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "단일 기관 불러오기", description = "단일 기관 레코드를 불러옵니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = PathVariable.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PathVariable.class)))})

    @GetMapping("/companies/{id}")
    public ResponseEntity<ApiR<?>> findOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companyService.findOne(id)));
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 지표
     **/
    @Operation(summary = "단일 기관 지표 점수 불러오기", description = "단일 기관 지표점수를 불러옵니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = PathVariable.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PathVariable.class)))})
    @GetMapping("/companies/{id}/jipyo")
    public ResponseEntity<ApiR<CompanyJipyoResponse>> findOneJipyo(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companyService.findOneJipyo(id)));
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 리뷰 슬라이더 탑 4
     **/
    @Operation(summary = "기관 별 지표 점수 불러오기", description = "기관 별 지표 점수 불러옵니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = PathVariable.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PathVariable.class)))})
    @GetMapping("/companies/jipyos")
    public ResponseEntity<ApiR<List<CompanyJipyoResponse>>> findJipyos(@PageableDefault(size = 4, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companyService.findJipyos(pageable)));
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 연봉리뷰 API
     */
    @Operation(summary = "기관 연봉 리뷰 단건 보기", description = "기업 리뷰를 조회합니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = CompanyReviewDetailResponseDto.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanyReviewDetailResponseDto.class)))})
    @GetMapping("/companies/{companyId}/review/amts/{id}")
    public ResponseEntity<ApiR<?>> amtReviewDetail(@PathVariable Long companyId, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(amtReviewService.amtReviewDetail(id)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "기관 평균 연봉 보기", description = "평균 연봉을 조회합니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))})
    @GetMapping("/companies/{companyId}/review/amts/avg")
    public ResponseEntity<ApiR<?>> amtReviewAvgList(@PathVariable Long companyId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(amtReviewService.amtReviewAvgList(companyId)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "기관 연봉 리뷰 보기", description = "기관에 작성된 연봉 리뷰를 조회합니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))})
    @GetMapping("/companies/{companyId}/review/amts")
    public ResponseEntity<ApiR<?>> companySearchAmt(@PathVariable Long companyId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(reviewService.companySearchAmt(companyId)));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 면접리뷰 API
     */
    @Operation(summary = "기관코드로 인터뷰 리뷰 보기", description = "기관에 작성된 인터뷰 리뷰를 조회합니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))})
    @GetMapping("/companies/{companyId}/review/interviews")
    public ResponseEntity<ApiR<?>> companySearchInterview(@PathVariable Long companyId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(reviewService.companySearchInterview(companyId)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "기관코드로 인터뷰 리뷰 상단 api", description = "기관에 작성된 인터뷰를 계산합니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))})
    @GetMapping("/companies/{companyId}/review/interviews/info")
    public ResponseEntity<ApiR<?>> companyInterviewInfo(@PathVariable Long companyId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(reviewService.companySearchInterviewCount(companyId)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "기관 면접 리뷰 단건 보기", description = "기업 리뷰를 조회합니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = CompanyReviewDetailResponseDto.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanyReviewDetailResponseDto.class)))})
    @GetMapping("/companies/{companyId}/review/interviews/{id}")
    public ResponseEntity<ApiR<?>> interviewReviewDetail(@PathVariable Long companyId, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(interviewService.interviewReviewDetail(id)));
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 기관리뷰 API
     */
    @Operation(summary = "기관코드로 기관 리뷰 보기", description = "기관에 작성된 기관 리뷰를 조회합니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))})
    @GetMapping("/companies/{companyId}/review/companies")
    public ResponseEntity<ApiR<?>> companySearchCompany(@PathVariable Long companyId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(reviewService.companySearchCompany(companyId)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "기관 리뷰 단건 보기", description = "기업 리뷰를 조회합니다..")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = CompanyReviewDetailResponseDto.class))), @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanyReviewDetailResponseDto.class)))})
    @GetMapping("/companies/{companyId}/review/companies/{id}")
    public ResponseEntity<ApiR<?>> companyReviewDetail(@PathVariable Long companyId, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companyReviewService.companyReviewDetail(id)));
        } catch (Exception e) {
            throw e;
        }
    }

}