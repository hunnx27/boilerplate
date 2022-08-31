package com.onz.modules.review.web.dto;

import lombok.Data;

@Data
public class YearAmtAvgResponseDto {
    private Long Amt;

    public YearAmtAvgResponseDto(Long Amt){
        this.Amt=getAmt();
    }
}
