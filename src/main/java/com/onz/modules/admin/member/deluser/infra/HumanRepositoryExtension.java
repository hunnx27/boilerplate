package com.onz.modules.admin.member.deluser.infra;

import com.onz.modules.admin.member.deluser.web.dto.DelUserListResponseDto;
import com.onz.modules.admin.member.deluser.web.dto.DelUserRequestDto;
import com.onz.modules.admin.member.deluser.web.dto.HumanListRequestDto;
import com.onz.modules.admin.member.deluser.web.dto.HumanListResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HumanRepositoryExtension {
    List<HumanListResponseDto> findByHumanList(HumanListRequestDto humanListRequestDto, Pageable pageable);

}
