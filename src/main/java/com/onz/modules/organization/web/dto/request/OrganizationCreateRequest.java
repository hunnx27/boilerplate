package com.onz.modules.organization.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;

@Getter
@Setter
@NoArgsConstructor
public class OrganizationCreateRequest {

    private String code;
    private String name;
    private String lat;
    private String lng;
    private int totalMemberCount;
    private int currentMemberCount;
}
