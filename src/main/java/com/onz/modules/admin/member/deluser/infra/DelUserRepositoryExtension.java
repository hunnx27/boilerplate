package com.onz.modules.admin.member.deluser.infra;

import com.onz.modules.admin.member.deluser.web.dto.DelUserListResponseDto;
import com.onz.modules.admin.member.deluser.web.dto.DelUserRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DelUserRepositoryExtension {
    List<DelUserListResponseDto> findByDelUser(DelUserRequestDto delUserRequestDto, Pageable pageable);
}
