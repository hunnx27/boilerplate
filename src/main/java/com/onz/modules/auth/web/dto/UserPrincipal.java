package com.onz.modules.auth.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onz.common.enums.Role;
import com.onz.modules.account.domain.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class UserPrincipal extends User implements OAuth2User, UserDetails {

    private final Account account;

    private Map<String, Object> attributes;

    public UserPrincipal(Account account) {
        super(account.getName(),
            account.getPassword(),
            Collections.singleton(new SimpleGrantedAuthority(account.getRole().getRole())));
        this.account = account;
    }

    public UserPrincipal(Account account, String password) {
        super(account.getEmail(),
            password,
            Collections.singleton(new SimpleGrantedAuthority(account.getRole().getRole())));
        this.account = account;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isEnabled() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(account.getRole().getRole()));
        return authList;
    }

    @Override
    public String getName() {
        return account.getName();
    }

    /**
     * OAUth2 로그인 시 비밀번호가 없기때문에 그대로 password가 null인채로 User클래스에 담게되면 에러가 발생함.
     * 이를 방지하기 위해 OAUth2일 시 password를 ""로 담는다.
     * @param account
     * @return
     */
    public static UserPrincipal to(Account account) {
        String password;
        if (StringUtils.hasText(account.getPassword())) {
            password = account.getPassword();
        } else {
            password = "";
        }

        return new UserPrincipal(account, password);
    }
}
