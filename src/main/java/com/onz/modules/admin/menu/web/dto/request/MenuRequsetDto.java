package com.onz.modules.admin.menu.web.dto.request;

import lombok.Getter;

@Getter
public class MenuRequsetDto {
    private String link;
    private Long mainCode;
    private Long subCode;
    private String subject;
    private String role;
}
