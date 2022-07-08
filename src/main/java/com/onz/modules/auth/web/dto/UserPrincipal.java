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

import java.util.*;

public class UserPrincipal implements OAuth2User, UserDetails {
    private Long id;
    private String userId;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public UserPrincipal(Long id, String userId, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Account account) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority(account.getRole().getRole()));

        return new UserPrincipal(
                account.getId(),
                account.getUserId(),
                account.getPassword(),
                authorities
        );
    }

    public static UserPrincipal create(Account account, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(account);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}
