package com.onz.modules.education.domain;

import com.onz.modules.account.domain.Account;
import com.onz.common.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Education extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String subject;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "education_account",
        joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "education_id", referencedColumnName = "id")
    )
    private Set<Account> accounts = new HashSet<>();

    private int accountsCount;

    public void addAccount(Account account) {
        this.getAccounts().add(account);
        this.accountsCount++;
    }

    public void removeAccount(Account account) {
        this.getAccounts().remove(account);
        this.accountsCount--;
    }

    public Education(Long id, String name, String subject, Set<Account> accounts,
        int accountsCount) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.accounts = accounts;
        this.accountsCount = accountsCount;
    }
}
