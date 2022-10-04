package com.onz.modules.admin.LiveMember.infra;

import com.onz.modules.admin.LiveMember.web.dto.*;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LiveMemberRepositoryExtension {

    List<LiveMemberResponseDto> findByLiveMember(LiveMemberRequestDto liveMemberRequestDto, Pageable pageable);
    LiveMemberResponseDto findByLiveMemberTotal(LiveMemberRequestDto liveMemberRequestDto);
    JPQLQuery<Long> findCountMember(LiveMemberRequestDto liveMemberRequestDto);
//    List<LiveMemberDetailResponse> findByAccountDetail(Long id);
    LiveMemberDetailResponse findByAccountDetail(Long id);
    LiveMemberPointResponse findByAccountPointDetail(Long id);
    List<LiveMemberPointListResponse> findByAccountPointList(Long id,Pageable pageable);
}

