package com.onz.modules.admin.member.deluser.web.dto;

import com.onz.common.enums.Gubn;
import com.onz.common.enums.InterestCompany;
import com.onz.common.enums.YN;
import com.onz.modules.account.domain.enums.AuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
public class DelUserResponse {

    private String userId;
    private long point;
    private InterestCompany interestCompany;
    private ZonedDateTime createAt;
    private YN isDelete;
    private Gubn gubn;
    private AuthProvider snsType;
    private String interestZone;

    public DelUserResponse(String userId, long point, String interestCompany, ZonedDateTime createAt, YN isDelete, String gubn, String snsType, String interestZone) {
        this.userId = userId;
        this.point = point;
        if (interestCompany != null && !"".equals(interestCompany)) {
            this.interestCompany = InterestCompany.valueOf(interestCompany);
        }
        this.createAt = createAt;
        this.isDelete = isDelete;
        if (gubn != null && !"".equals(gubn)) {
            this.gubn = Gubn.valueOf(gubn);
        }
        if (snsType != null && !"".equals(gubn)) {
            this.snsType = AuthProvider.valueOf(snsType);
        }
        this.interestZone = interestZone;
    }
}
