package com.onz.modules.admin.auth.web.dto;

import lombok.Getter;

@Getter
public class AdminCreateRequestDto {

    private String userId;
    private String team;
    private String name;
    private String pw;
    private String useYn;


}
