package com.onz.modules.account.domain;

import com.onz.modules.account.domain.enums.AuthProvider;
import com.onz.modules.account.web.dto.request.AccountUpdateRequest;
import com.onz.common.enums.Role;
import com.onz.common.domain.BaseEntity;
import com.onz.modules.education.domain.Education;
import com.onz.modules.organization.domain.Organization;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonIgnore
    private String password;
    @NotNull
    @Column(unique = true)
    private String email;
    private String age;
    private String location;

    private boolean isEmailVerified;
    private String profileImage;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider = AuthProvider.local;

    @ManyToMany(mappedBy = "accounts")
    @JsonBackReference
    private Set<Education> educations = new HashSet<>();

    @ManyToOne
    private Organization organization;

    @JsonIgnore
    @OneToOne(mappedBy = "director", fetch = FetchType.LAZY)
    private Organization director;

    @Builder
    public Account(String name, String email, String picture, Role role, AuthProvider provider) {
        this.name = name;
        this.email = email;
        this.profileImage = picture;
        this.role = role;
        this.provider = provider;
    }

    public void setUpdateData(AccountUpdateRequest account) {
        this.name = account.getName();
        this.email = account.getEmail();
        this.age = account.getAge();
        this.location = account.getLocation();
    }

    public Account update(String name, String picture) {
        this.name = name;
        this.profileImage = picture;
        if (Objects.isNull(this.role)) {
            this.role = Role.USER;
        }

        return this;
    }
}
