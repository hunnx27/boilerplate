package com.onz.modules.organization.web.dto.request;

import com.onz.common.web.dto.request.BasePageRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrganizationSearchRequest extends BasePageRequest {


    private String code;
    private String name;
    private String address;
}
