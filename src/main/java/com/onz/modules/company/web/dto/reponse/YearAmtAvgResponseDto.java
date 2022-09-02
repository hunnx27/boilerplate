package com.onz.modules.company.web.dto.reponse;

import lombok.Data;

@Data
public class YearAmtAvgResponseDto {
    private Long totalAmt;

    public YearAmtAvgResponseDto(){
        this.totalAmt=getTotalAmt();
    }
}
