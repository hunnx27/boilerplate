package com.onz.modules.common.address.infra;

import com.onz.modules.account.domain.Account;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AddressRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        AddressRepositoryExtension {

    private final JPAQueryFactory qf;
    private final EntityManager em;

    public AddressRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Account.class);
        this.qf = qf;
        this.em = em;
    }


}
