package com.onz.modules.admin.auth.web.dto;

import com.onz.common.enums.Role;
import com.onz.common.enums.YN;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Getter
public class AdminCreateRequestDto {

    private String userId;
    private String team;
    private String name;
    private String pw;
    private String useYn;


}
