package com.onz.modules.auth.web.dto.request;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String name;
    private String password;
}
