package com.onz.modules.company.web.dto.reponse;

import com.onz.common.enums.YN;
import com.onz.modules.review.domain.InterviewReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
public class InterviewListResponseDto {
    private String type = "INTERVIEW";
    @JsonIgnore
    private Long id;
    private String zonecode;
    private String mapsidogunguName;
    private String establishmentType;
    private String officeName;

    private String item_1;
    private YN item_2;
    private YN item_3;
    private String item_4;
    private String item_5;
    private YN item_6;
    private String q_1;
    private YN workExpOpenYn;
    private Long workExp;
    private int writCount;
    private int patCount;

    public InterviewListResponseDto(InterviewReview interviewReview) {
        this.id = interviewReview.getId();
        this.zonecode = interviewReview.getCompany().getZonecode();
        this.item_1 = interviewReview.getItem_1();
        this.item_2 = interviewReview.getItem_2();
        this.item_3 = interviewReview.getItem_3();
        this.item_4 = interviewReview.getItem_4();
        this.item_5 = interviewReview.getItem_5();
        this.item_6 = interviewReview.getItem_6();
        this.q_1 = interviewReview.getTopQ1();
        this.establishmentType = interviewReview.getCompany().getEstablishmentType().getName();
        this.officeName = interviewReview.getCompany().getOfficeName();
        this.mapsidogunguName = getMapsidogunguName();
        this.workExp = interviewReview.getWorkExp();
        this.workExpOpenYn = interviewReview.getWorkExpOpenYn();
    }
}
