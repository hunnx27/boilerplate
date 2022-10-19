package com.onz.modules.common.code.web.dto;

import com.onz.common.web.dto.response.enums.YN;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class CommonCodeInitRequestDto {

    private Long id;
    private String code;
    private String codeSebu;
    private String codeName;
    private ZonedDateTime createDt;
    private YN useYn;
    private String bigo;

}
