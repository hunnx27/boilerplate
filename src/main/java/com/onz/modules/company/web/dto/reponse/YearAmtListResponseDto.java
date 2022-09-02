package com.onz.modules.company.web.dto.reponse;

import com.onz.common.enums.YN;
import com.onz.modules.company.domain.enums.EstablishmentType;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.domain.dto.ReviewAll;
import com.onz.modules.review.web.dto.ReviewCommonResponseDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumSet;

@Data
@NoArgsConstructor
public class YearAmtListResponseDto extends ReviewCommonResponseDto {
    private String type="AMT";
    private Long amt;
    private Long totalCost;
    private String impCost;
    private String workCost;
    private String addCost;
    private String etcCost;
    private String EtcItems;
    private String EtcAmt;

    public YearAmtListResponseDto(YearAmtReview yearAmtReview){
        this.workExp= yearAmtReview.getWorkExp();
        this.workExpOpenYn=yearAmtReview.getWorkExpOpenYn();
        this.companyId=yearAmtReview.getCompany().getId();
        this.amt=yearAmtReview.getAmt();
        this.companyName=yearAmtReview.getCompany().getOfficeName();
        this.mapsidogunguName=yearAmtReview.getMapsidogunguName();
        this.id=yearAmtReview.getId();
        this.establishmentTypeName=yearAmtReview.getCompany().getEstablishmentType().getName();
        this.zoneCode=yearAmtReview.getCompany().getZonecode();
        this.EtcAmt=yearAmtReview.getEtcAmt();
        this.EtcItems=yearAmtReview.getEtcItems();
        this.accountId= yearAmtReview.getAccount().getId();
        this.totalCost=getTotalCost();
        //TODO
        //TODO
        //TODO
        //TODO Switch 문 추가 예정
    }
}
