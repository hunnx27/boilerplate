package com.onz.modules.account.application;

import com.onz.modules.account.application.request.AccountUpdateRequest;
import com.onz.modules.account.domain.Account;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountConverter {

    void updateAccountFromDto(AccountUpdateRequest accountUpdateRequest,
        @MappingTarget Account account);

}
