package com.onz.config.security.oauth2;

import com.onz.config.security.exception.CustomAuthenticationException;
import com.onz.modules.account.domain.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name,
        String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String id, String name, Map<String, Object> attributes) {
        if (RestrictionProvider.of(id) == null) {
            throw new CustomAuthenticationException(id + " 는 호환되지 않는 소셜 로그인입니다.");
        }

        if (id.equalsIgnoreCase(RestrictionProvider.KAKAO.name())) {
            return ofKakao(name, attributes);
        } else if (id.equalsIgnoreCase(RestrictionProvider.GOOGLE.name())) {
            return ofGoogle(name, attributes);
        }

        return null;
    }

    private static OAuthAttributes ofKakao(String name, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuthAttributes.builder()
            .name((String) profile.get("nickname"))
            .email((String) kakaoAccount.get("email"))
            .picture((String) profile.get("profile_image_url"))
            .nameAttributeKey(name)
            .attributes(attributes)
            .build();
    }

    private static OAuthAttributes ofGoogle(String name, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
            .name((String) attributes.get("name"))
            .email((String) attributes.get("email"))
            .picture((String) attributes.get("picture"))
            .attributes(attributes)
            .nameAttributeKey(name)
            .build();
    }

    public Account toEntity() {
        return new Account(name, email, picture);
    }
}
