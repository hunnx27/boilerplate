package com.onz.modules.admin.member.deluser.web.dto;

import com.onz.common.domain.BaseEntity;
import com.onz.common.enums.Gubn;
import com.onz.common.web.dto.request.BasePageRequest;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class DelUserRequestDto extends BasePageRequest {

    private Gubn gubn;
    private final String deletedAtA;
    private final String deletedAtB;
    private final String userId;


    public DelUserRequestDto(String gubn, String deletedAtA, String deletedAtB, String userId) {
        if (gubn != null && !"".equals(gubn)) {
            this.gubn = Gubn.valueOf(gubn);
        }
        this.deletedAtA = deletedAtA;
        this.deletedAtB = deletedAtB;
        this.userId = userId;
    }
}
