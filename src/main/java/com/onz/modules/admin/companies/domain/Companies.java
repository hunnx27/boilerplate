package com.onz.modules.admin.companies.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.account.domain.Account;
import com.onz.modules.company.domain.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Companies {

    @Id
    @GeneratedValue
    private Long id;
    //Account 에서 받아오기
    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private ZonedDateTime requestEdt;
    private String fixText;
    private String userId;
    //기관정보
    //company에서 받아오기

    //처리현황 adminid는 account에서 받아오기
    private String apprId;
    @Enumerated(EnumType.STRING)
    private State state;
    private String apprTxt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @Builder
    public Companies(Long id, String fixText, String apprId, State state, String apprTxt, Account account, Company company,String userId) {
        this.id = id;
        this.fixText = fixText;
        this.apprId = apprId;
        this.state = state;
        this.apprTxt = apprTxt;
        this.account = account;
        this.company = company;
        this.userId=userId;
    }

    public Companies(Companies companies) {
        this.id = companies.getId();
        this.requestEdt = companies.getRequestEdt();
        this.fixText = companies.getFixText();
        this.apprId = companies.getApprId();
        this.apprTxt = companies.getApprTxt();
        this.account = companies.getAccount();
        this.company = companies.getCompany();
    }
}
