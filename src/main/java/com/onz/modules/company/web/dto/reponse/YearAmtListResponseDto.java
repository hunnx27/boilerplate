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
import java.util.HashMap;
import java.util.Map;

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
        String[] one = this.getEtcItems().split(",");
        String[] two = this.getEtcAmt().split(",");
        int total = 0;
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < one.length; i++) {
            String key = one[i];
            String value = two[i];
            map.put(key, value);
            total += Integer.parseInt(value);

            switch (key) {
                case "1":
                    System.out.println(key + "+" + value);
                    this.setImpCost(value);
                    break;
                case "2":
                    System.out.println(key + "+" + value);
                    this.setWorkCost(value);
                    break;
                case "3":
                    System.out.println(key + "+" + value);
                    this.setAddCost(value);
                    break;
                case "4":
                    System.out.println(key + "+" + value);
                    this.setEtcCost(value);
                    break;
            }
            this.setTotalCost((long) total);
        }
    }
}
