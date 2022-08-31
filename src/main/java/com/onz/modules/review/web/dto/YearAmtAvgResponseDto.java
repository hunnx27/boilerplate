package com.onz.modules.review.web.dto;

import lombok.Data;

@Data
public class YearAmtAvgResponseDto {
    private Long totalAmt;

    public YearAmtAvgResponseDto(){
        this.totalAmt=getTotalAmt();
    }
}
