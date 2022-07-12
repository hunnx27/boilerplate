package com.onz.modules.account.web.dto.response;

import com.onz.common.enums.Role;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.enums.AuthProvider;
import com.onz.modules.account.domain.enums.Gubn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponse {

    private Long id;
    private String userId;
    private Role role;
    private AuthProvider snsType;
    private long point;
    private Gubn gubn;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.userId = account.getUserId();
        this.role = account.getRole();
        this.snsType = account.getSnsType();
        this.point = account.getPoint();
        this.gubn = account.getGubn();
    }
}
