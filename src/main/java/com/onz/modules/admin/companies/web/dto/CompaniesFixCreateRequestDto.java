package com.onz.modules.admin.companies.web.dto;

import com.onz.common.web.dto.response.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CompaniesFixCreateRequestDto {
    private String fixText;
    private Long companyId;
}
