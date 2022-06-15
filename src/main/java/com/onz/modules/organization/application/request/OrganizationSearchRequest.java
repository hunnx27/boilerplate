package com.onz.modules.organization.application.request;

import com.onz.modules.common.application.request.BasePageRequest;
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
