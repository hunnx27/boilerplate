package com.onz.modules.account.domain;

import com.onz.common.enums.YN;
import com.onz.modules.account.domain.enums.AuthProvider;
import com.onz.modules.account.domain.enums.AuthProviderConverter;
import com.onz.modules.account.domain.enums.Gubn;
import com.onz.modules.account.domain.enums.GubnConverter;
import com.onz.modules.account.web.dto.request.AccountUpdateRequest;
import com.onz.common.enums.Role;
import com.onz.common.domain.BaseEntity;
import com.onz.modules.auth.application.util.MysqlAESUtil;
import com.onz.modules.auth.application.util.MysqlSHA2Util;
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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

    @NotNull
    @Column(unique = true)
    private String userId;
    @JsonIgnore
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    //@Enumerated(EnumType.STRING)
    @Convert(converter = AuthProviderConverter.class)
    private AuthProvider snsType = AuthProvider.local;

    //@Enumerated(EnumType.STRING)
    @Convert(converter = GubnConverter.class)
    private Gubn gubn = Gubn.PARENT;

    private long point;

    private String temp;

    @ManyToMany(mappedBy = "accounts")
    @JsonBackReference
    private Set<Education> educations = new HashSet<>();

    @ManyToOne
    private Organization organization;

    @JsonIgnore
    @OneToOne(mappedBy = "director", fetch = FetchType.LAZY)
    private Organization director;

    @Builder
    public Account(String userId, Gubn gubn, Role role, AuthProvider provider) {
        this.gubn = gubn;
        this.snsType = provider;
        this.role = role;
        this.temp = userId; // userId 임시보관.

        if(gubn != null) {
            // Final Step;
            byte[] encode = new byte[0];
            try {
                String key = String.format("%s%s%s", "ONZ!@#", this.gubn.getCode(), this.snsType.getCode());
                encode = MysqlAESUtil.encryptoByte(key, userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String encodedUserId = MysqlSHA2Util.getSHA512(encode);
            this.userId = encodedUserId;
        }else{
            // First Step
            this.userId = userId; // plained UserId;
        }
    }

    public void setUpdateData(AccountUpdateRequest account) {
        String userId = account.getUserId();
        this.temp = userId;

        if(userId !=null){
            byte[] encode = new byte[0];
            try {
                String key = String.format("%s%s%s", "ONZ!@#", this.gubn.getCode(), this.snsType.getCode());
                encode = MysqlAESUtil.encryptoByte(key, userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String encodedUserId = MysqlSHA2Util.getSHA512(encode);
            this.userId = encodedUserId;
        }
    }

    public Account update(String registrationId) {
        AuthProvider snsType = AuthProvider.valueOf(registrationId);
        if (Objects.isNull(this.snsType)) {
            this.snsType = snsType;
        }
        return this;
    }
}
