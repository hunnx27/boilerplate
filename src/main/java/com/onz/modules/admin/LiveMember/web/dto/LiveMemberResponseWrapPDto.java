package com.onz.modules.admin.LiveMember.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class LiveMemberResponseWrapPDto {
    private LiveMemberPointResponse total;
    private List<LiveMemberPointListResponse> list;

    public LiveMemberResponseWrapPDto(LiveMemberPointResponse total, List<LiveMemberPointListResponse> list) {
        this.total = total;
        this.list = list;
    }
}
