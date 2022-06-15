package com.onz.modules.account.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountSearchRequest {

    private String name;
    private String email;
}
