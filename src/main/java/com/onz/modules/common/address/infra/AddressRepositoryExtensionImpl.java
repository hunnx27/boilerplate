package com.onz.modules.common.address.infra;

import com.onz.common.domain.QAddress;
import com.onz.modules.common.address.domain.Address;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AddressRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        AddressRepositoryExtension {

    private final JPAQueryFactory qf;
    private final EntityManager em;

    public AddressRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Address.class);
        this.qf = qf;
        this.em = em;
    }


//    @Override
    public List<Address> findByAddressSido() {
        // QueryDSL
        QAddress address = new QAddress("address");

        // 조건생성
        BooleanBuilder where = new BooleanBuilder();
//        where.and(account.userId.eq(encodedUserId));

        // 쿼리생성
//        List<Address> findedAddress = qf.selectFrom(address)
//                .where(where)
//                .groupBy()
//                .fetchAll();


        return null;
    }


}
