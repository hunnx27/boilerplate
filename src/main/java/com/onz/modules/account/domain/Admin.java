package com.onz.modules.account.domain;

import com.onz.common.domain.BaseEntity;
import com.onz.common.enums.Role;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String adminId;
    private String adminPw;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String name;
    private ZonedDateTime lastLogin;
    private String authKey;
    private String team;
}
