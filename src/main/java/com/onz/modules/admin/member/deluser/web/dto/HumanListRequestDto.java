package com.onz.modules.admin.member.deluser.web.dto;

import com.onz.common.enums.Gubn;
import com.onz.common.web.dto.request.BasePageRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class HumanListRequestDto extends BasePageRequest {

    private Gubn gubn;
    private final String createdAtA;
    private final String createdAtB;
    private String userId;

    public HumanListRequestDto(String gubn, String createdAtA, String createdAtB, String userId) {
        if (gubn != null && !"".equals(gubn)) {
            this.gubn = Gubn.valueOf(gubn);
        }
        this.createdAtA = createdAtA;
        this.createdAtB = createdAtB;
        this.userId = userId;
    }
}
