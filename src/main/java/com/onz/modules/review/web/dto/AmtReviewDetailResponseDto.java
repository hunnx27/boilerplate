package com.onz.modules.review.web.dto;

import com.onz.common.enums.YN;
import com.onz.modules.review.domain.YearAmtReview;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class AmtReviewDetailResponseDto {

    private Long amt;
    private Long totalCost;
    private String impCost;
    private String workCost;
    private String addCost;
    private String ectCost;
    private Long workExp;
    private YN endAtmYn;
    private String etcAmt;
    public AmtReviewDetailResponseDto(YearAmtReview yearAmtReview) {
        this.amt=yearAmtReview.getAmt();
        this.etcAmt=yearAmtReview.getEtcAmt();
        this.workExp=yearAmtReview.getWorkExp();
        this.endAtmYn=yearAmtReview.getEndAtmYn();
        int total = 0;
        String[] one = yearAmtReview.getEtcItems().split(",");
        String[] two = yearAmtReview.getEtcAmt().split(",");
        for (int i = 0; i < one.length; i++) {
            String key = one[i];
            String value = two[i];
            Map<String, String> map = new HashMap<>();
            map.put(key, value);
            total += Integer.parseInt(value);

            switch (key) {
                case "1":
                    System.out.println(key + "+" + value);
                    this.impCost=value;
                    break;
                case "2":
                    System.out.println(key + "+" + value);
                    this.workCost=value;
                    break;
                case "3":
                    System.out.println(key + "+" + value);
                    this.addCost=value;
                    break;
                case "4":
                    System.out.println(key + "+" + value);
                    this.ectCost=value;
                    break;
            }
            this.totalCost=(long) total;
        }
    }
}
