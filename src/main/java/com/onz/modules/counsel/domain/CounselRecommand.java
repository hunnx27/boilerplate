package com.onz.modules.counsel.domain;

import com.onz.common.domain.BaseEntity;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CounselRecommand extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name="account_id")
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name="counsel_id")
    private Counsel counsel;
    @Enumerated(EnumType.STRING)
    private RecommandGubn recommandGubn;

    public CounselRecommand() {
    }

}
