package com.onz.modules.admin.LiveMember.infra;

import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.LiveMember.domain.LiveMemberRequestDto;
import com.onz.modules.admin.LiveMember.domain.LiveMemberResponseDto;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface LiveMemberRepositoryExtension {

    List<LiveMemberResponseDto> findByLiveMember(LiveMemberRequestDto liveMemberRequestDto);
}
