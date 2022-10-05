package com.onz.modules.admin.member.deluser.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.admin.member.deluser.application.DelUserService;
import com.onz.modules.admin.member.deluser.web.dto.DelUserRequestDto;
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
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Tag(name = "어드민 제어", description = "어드민을 제어하는 api.")
public class DelUserController {

    private final DelUserService delUserService;
    @Operation(summary = "탈퇴 회원 관리 ", description = "탈퇴 관리입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = DelUserRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = DelUserRequestDto.class)))
    })
    @GetMapping("/admin/deleted/")
    public ResponseEntity<ApiR<?>> delUserList(HttpServletResponse response, DelUserRequestDto delUserRequestDto, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(delUserService.delUserList(response,delUserRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
}