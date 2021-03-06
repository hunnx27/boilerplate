package com.onz.modules.account.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountUpdateRequest {

    private Long id;
    private String name;
    private String userId;
    private String age;
    private String location;

}
