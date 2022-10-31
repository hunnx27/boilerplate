package com.onz.modules.admin.faq.web.dto;

import lombok.Data;

@Data
public class FaqCreateRequestDto {
    private String deviceGubn;
    private String category;
    private String title;
    private String content;
    private String useYn;
}
