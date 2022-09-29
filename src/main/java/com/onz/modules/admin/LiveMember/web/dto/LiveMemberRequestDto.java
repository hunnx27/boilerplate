package com.onz.modules.admin.LiveMember.web.dto;

import com.onz.common.enums.Gubn;
import com.onz.common.web.dto.request.BasePageRequest;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
public class LiveMemberRequestDto extends BasePageRequest {

    private Gubn gubn = null;
    private final String userId;
    private final String createAtA;
    private final String createAtD;
    private final String birthYYYYo;
    private final String birthYYYYt;
    private final String sido;
    private final String sigungu;
    private String reviewCount;
    private String counselQCount;
    private String counselACount;
    private Long reviewCountNum;
    private Long counselQCountNum;
    private Long counselACountNum;
    private String options;

    public LiveMemberRequestDto(String gubn, String userId, String createAtA, String createAtD, String birthYYYYo, String birthYYYYt, String sido, String sigungu
            , String reviewCount, String counselQCount, String counselACount, Long reviewCountNum, Long counselQCountNum, Long counselACountNum,String options) {
        if (gubn != null && !"".equals(gubn)) {
            this.gubn = Gubn.valueOf(gubn);
        }
        this.options=options;
        this.reviewCount=reviewCount;
        this.counselQCount=counselQCount;
        this.counselACount=counselACount;
        this.reviewCountNum=reviewCountNum;
        this.counselQCountNum=counselQCountNum;
        this.counselACountNum=counselACountNum;
        this.userId = userId;
        this.birthYYYYo = birthYYYYo;
        this.birthYYYYt = birthYYYYt;
        this.createAtD = createAtD;
        this.createAtA = createAtA;
        this.sido = sido;
        this.sigungu = sigungu;
    }
}
