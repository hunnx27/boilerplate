package com.onz.modules.admin.companies.web.dto;

import com.onz.common.web.dto.response.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CompaniesCreateRequestDto {

    private String txt;

}
