package com.onz.modules.admin.notice.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeSearchRequestDto {
    private String createAdt;
    private String createBdt;
    private String deviceGubn;
    private String txt;
}
