package com.onz.modules.education.web;

import com.onz.common.web.BaseApiController;
import com.onz.modules.common.address.web.dto.AddressResponse;
import com.onz.modules.education.application.EducationService;
import com.onz.modules.education.web.dto.request.EducationCreateRequest;
import com.onz.modules.education.web.dto.request.EducationSearchRequest;
import com.onz.modules.education.web.dto.request.EducationUpdateRequest;
import com.onz.modules.education.domain.Education;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name="교육 제어",description = "교육 레코드를 제어하는 api.")
public class EducationController extends BaseApiController {

    private final EducationService eduCationService;
    private final ModelMapper modelMapper;

    @Operation(summary = "교육 레코드 생성", description = "교육 레코드를 생성합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "생성 완료", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = EducationCreateRequest.class)))
    })
    @PostMapping("/educations")
    public ResponseEntity<?> create(@RequestBody EducationCreateRequest educationCreateRequest) {
        return ResponseEntity.ok(
            eduCationService.create(modelMapper.map(educationCreateRequest, Education.class)));
    }

    @Operation(summary = "교육 레코드 조회하기", description = "교육 레코드를 불러옵니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = EducationSearchRequest.class)))
    })
    @GetMapping("/educations")
    public ResponseEntity<?> list(
        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)
        Pageable pageable,
        EducationSearchRequest educationSearchRequest) {
        return ResponseEntity.ok(eduCationService.list(educationSearchRequest, pageable));
    }

    @Operation(summary = "교육 레코드 리스트 조회", description = "교육 레코드를 조회합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PathVariable.class)))
    })
    @GetMapping("/educations/{id}")
    public ResponseEntity<?> list(@PathVariable Long id) {
        return ResponseEntity.ok(eduCationService.detail(id));
    }

    @Operation(summary = "교육 레코드 수정", description = "교육 레코드를 수정합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 완료", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = EducationUpdateRequest.class)))
    })
    @PutMapping("/educations/{id}")
    public ResponseEntity<?> update(@RequestBody EducationUpdateRequest educationUpdateRequest,
        @PathVariable Long id) {
        educationUpdateRequest.setId(id);
        return ResponseEntity.ok(eduCationService.update(educationUpdateRequest));
    }

    @Operation(summary = "교육 레코드 삭제", description = "교육 레코드를 삭제합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 완료", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PathVariable.class)))
    })
    @DeleteMapping("/educations/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(eduCationService.delete(id));
    }

    @Operation(summary = "교육 레코드 조인", description = "교육 레코드를 조인합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조인 완료", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PathVariable.class)))
    })
    @PostMapping("/educations/{id}/join")
    public ResponseEntity<?> join(@PathVariable Long id, Long accountId) {
        return ResponseEntity.ok(eduCationService.join(id, accountId));
    }

    @Operation(summary = "교육 레코드 떠나기", description = "교육 레코드를 변경합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "분리 완료", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PathVariable.class)))
    })
    @PostMapping("/educations/{id}/leave")
    public ResponseEntity<?> leave(@PathVariable Long id, Long accountId) {
        return ResponseEntity.ok(eduCationService.leave(id, accountId));
    }

}
