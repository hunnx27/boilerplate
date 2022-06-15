package com.onz.modules.account.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountCreateRequest {

    private String name;
    private String email;
    private String age;
    private String location;
    private String password;

}
