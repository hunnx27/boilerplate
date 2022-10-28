package com.onz.modules.admin.notice.web.dto;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.admin.notice.domain.enums.DeviceGubn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeRequestDto {
    private String title;
    private String deviceGubn;
    private String useYn;
    private String content;
}
