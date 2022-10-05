package com.onz.modules.admin.member.livemember.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.member.livemember.application.LiveMemberService;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberDetailResponse;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberRequestDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseDto;
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
public class LiveMemberController {

    private final LiveMemberService liveMemberService;

    @Operation(summary = "라이브 회원 관리 ", description = "회원 관리입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class)))
    })
    @GetMapping("/admin/liveMember")
    public ResponseEntity<ApiR<?>> liveMember(HttpServletResponse response, LiveMemberRequestDto liveMemberRequestDto, Pageable pageable) {
//        liveMemberService.liveMember(response,liveMemberRequestDto);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(liveMemberService.liveMember(response,liveMemberRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = Account.class)))
    })
    @GetMapping("/admin/liveMember/{id}")
    public ResponseEntity<ApiR<LiveMemberDetailResponse>> liveMemberDetail(HttpServletResponse response, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(liveMemberService.liveMemberDetail(response,id)));
        } catch (Exception e) {
            throw e;
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = Account.class)))
    })
    @GetMapping("/admin/liveMember/point/{id}")
    public ResponseEntity<ApiR<?>> liveMemberResponseWrapPDto(HttpServletResponse response, @PathVariable Long id, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(liveMemberService.liveMemberResponseWrapPDto(response,id,pageable)));
        } catch (Exception e) {
            throw e;
        }
    }


}