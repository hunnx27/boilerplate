package com.onz.modules.auth.application.util;


import com.onz.modules.account.domain.enums.AuthProvider;
import com.onz.modules.auth.web.dto.oauth.GoogleOAuth2UserInfo;
import com.onz.modules.auth.web.dto.oauth.KakaoOAuth2UserInfo;
import com.onz.modules.auth.web.dto.oauth.NaverOAuth2UserInfo;
import com.onz.modules.auth.web.dto.oauth.OAuth2UserInfo;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
            return new KakaoOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.naver.toString())) {
            return new NaverOAuth2UserInfo(attributes);
//        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
//            return new FacebookOAuth2UserInfo(attributes);
//        } else if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
//            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
