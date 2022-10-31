package com.onz.modules.admin.event.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.admin.companies.web.dto.CompaniesResponseDto;
import com.onz.modules.admin.event.application.EventService;
import com.onz.modules.admin.event.web.dto.EventCreateRequestDto;
import com.onz.modules.admin.event.web.dto.EventSearchRequestDto;
import com.onz.modules.admin.notice.web.dto.NoticeRequestDto;
import com.onz.modules.admin.notice.web.dto.NoticeSearchRequestDto;
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
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "어드민 이벤트 제어", description = "어드민 이벤트 제어관련 api")
public class EventController {

        private final EventService eventService;

    @Operation(summary = "이벤트 등록", description = "이벤트를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = EventCreateRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = EventCreateRequestDto.class)))
    })
    @PostMapping("/admin/event")
    public void create(@AuthenticationPrincipal UserPrincipal me, EventCreateRequestDto eventCreateRequestdto) {
        try {
            eventService.create(eventCreateRequestdto, me);
            ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccessWithNoContent());
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "이벤트 검색  ", description = "이벤트 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/event")
    public ResponseEntity<ApiR<?>> eventSearch(EventSearchRequestDto eventSearchRequestDto, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(eventService.eventSearch(eventSearchRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "이벤트 단일 선택  ", description = "이벤트 단일 선택 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/event/{id}")
    public ResponseEntity<ApiR<?>> eventSearchDetail(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal me) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(eventService.eventSearchDetail(id,me)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "이벤트 업데이트  ", description = "이벤트 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @PutMapping("/admin/event/{id}")
    public ResponseEntity<ApiR<?>> eventSearchDetailfix(@PathVariable Long id, EventCreateRequestDto eventCreateRequestDto, @AuthenticationPrincipal UserPrincipal me) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(eventService.eventSearchDetailfix(id,eventCreateRequestDto,me)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "이벤트 삭제  ", description = " 이벤트 삭제 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @DeleteMapping("/admin/event/{id}")
    public ResponseEntity<ApiR<?>> eventSearchDelete(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal me) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(eventService.eventSearchDelete(id,me)));
        } catch (CustomException e) {
            throw e;
        }
    }
}
