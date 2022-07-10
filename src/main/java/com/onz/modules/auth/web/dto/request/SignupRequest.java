package com.onz.modules.auth.web.dto.request;

import lombok.Getter;


@Getter
public class SignupRequest {
    //{ "socialId": "3295686887319897", "snsTypeCode": "F", "agree": true, "gubnCode": "A" }
    private String socialId;
    private String snsTypeCode;
    private String agree;
    private String gubnCode;
}
