package com.onz.modules.admin.counsels.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class CounselsWrapResponseDto {
    private CountEvent count;
    private List<CounselsResponseDto> list;

    public CounselsWrapResponseDto(CountEvent count, List<CounselsResponseDto> list) {
        this.count = count;
        this.list = list;
    }
}
