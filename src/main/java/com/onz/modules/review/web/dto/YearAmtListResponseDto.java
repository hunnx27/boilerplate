package com.onz.modules.review.web.dto;

import com.onz.common.enums.YN;
import com.onz.common.web.BaseApiController;
import com.onz.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
public class YearAmtListResponseDto extends BaseApiController {
    private String type="AMT";
    private Long workExp; // 근무시 교사 연차
    private YN workExpOpenYn; // 근무시 교사 연차 공개 여부
    private Long companyId;
    private String officeName;
    private String establishmentType;
    private String mapsidogunguName;
    private String Zonecode;
    //2 page
    private Long amt; // 연봉
    private YN endAtmYn; // 퇴직금 여부
    private YN etcYn; //수당 여부
    //2-1 page 수당
    private String etcAmt; // 수당금액 배열 , 기준으로 etc_items와 매핑된다
    private String etcItems; //입력한 수당 idx 배열 {1 - 처우개선비 ,2 - 근무환경개선비, 3- 누리과정수당, 4- 기타
    @JsonIgnore
    private Long id;
    private String impCost;
    private String workCost;
    private String addCost;
    private String etcCost;
    private Long totalCost;

    public YearAmtListResponseDto(Long workExp, YN workExpOpenYn, Long companyId, Long amt, YN endAtmYn, String etcItems, String officeName, String mapsidogunguName, Long id,String Zonecode,YN etcYn,EstablishmentType establishmentType,String etcAmt){
        this.workExp=workExp;
        this.workExpOpenYn=workExpOpenYn;
        this.companyId=companyId;
        this.amt=amt;
        this.etcAmt=etcAmt;
        this.endAtmYn=endAtmYn;
        this.etcItems=etcItems;
        this.officeName=officeName;
        this.mapsidogunguName=mapsidogunguName;
        this.id=id;
        this.etcYn=etcYn;
        this.Zonecode=getZonecode();
        this.establishmentType=establishmentType.getName();
    }
}
