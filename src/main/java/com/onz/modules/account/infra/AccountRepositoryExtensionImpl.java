package com.onz.modules.account.infra;

import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.QAccount;
import com.onz.modules.account.domain.enums.AuthProvider;
import com.onz.common.enums.Gubn;
import com.onz.modules.account.web.dto.request.AccountSearchRequest;
import com.onz.common.enums.YN;
import com.onz.modules.auth.application.util.MysqlAESUtil;
import com.onz.modules.auth.application.util.MysqlSHA2Util;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimpleTemplate;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AccountRepositoryExtensionImpl extends QuerydslRepositorySupport implements
    AccountRepositoryExtension {

    private final JPAQueryFactory qf;
    private final EntityManager em;

    public AccountRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Account.class);
        this.qf = qf;
        this.em = em;
    }

    @Override
    public Page<Account> accounts(AccountSearchRequest accountSearchRequest, Pageable pageable) {
        QAccount account = QAccount.account;

        BooleanBuilder where = new BooleanBuilder();
        where.and(account.isDelete.eq(YN.N));

        if (StringUtils.hasText(accountSearchRequest.getUserId())) {
            where.or(account.userId.containsIgnoreCase(accountSearchRequest.getUserId()));
        }

        JPQLQuery<Account> result = from(account)
            .where(where);

        JPQLQuery<Account> query = Objects
            .requireNonNull(getQuerydsl())
            .applyPagination(pageable, result);

        QueryResults<Account> queryResults = query.fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    @Override
    public Account deleteAccount(Long id) {
        QAccount account = QAccount.account;
        qf.update(account)
            .set(account.isDelete, YN.Y)
            .where(account.id.eq(id)
                .and(account.isDelete.eq(YN.N)))
            .execute();
        return from(account)
            .where(account.id.eq(id))
            .fetchOne();
    }


    @Override
    @Deprecated
    /**
     * @Deprecated
     * ????????? ?????? ??????(?????? ???????????? ????????? ??????)
     */
    public Optional<Account> findByPlainUserId(String plainUserId, AuthProvider snsType) {
        byte[] encode1 = new byte[0];
        byte[] encode2 = new byte[0];
        try {
            String key1 = String.format("%s%s%s", "ONZ!@#", Gubn.TEACHER.getCode(), snsType.getCode());
            String key2 = String.format("%s%s%s", "ONZ!@#", Gubn.PARENT.getCode(), snsType.getCode());
            encode1 = MysqlAESUtil.encryptoByte(key1, plainUserId);
            encode2 = MysqlAESUtil.encryptoByte(key2, plainUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String encodedUserId1 = MysqlSHA2Util.getSHA512(encode1);
        String encodedUserId2 = MysqlSHA2Util.getSHA512(encode2);

        // QueryDSL
        QAccount account = QAccount.account;

        CriteriaBuilder cb = em.getCriteriaBuilder();


        // ????????????
        BooleanBuilder where = new BooleanBuilder();
        where
            .and(account.userId.eq(encodedUserId1))
            .or(account.userId.eq(encodedUserId2));
        JPQLQuery q = from(account).where(where);
        QueryResults<Account> queryResults = q.fetchResults();

        // ??????????????????
        Account result = queryResults.getResults().stream().findFirst().orElse(null);
        Optional<Account> optionalAccount = Optional.ofNullable(result);
        return optionalAccount;
    }

    @Override
    public Optional<Account> findByPlainUserId2(String plainUserId) {
        String query = "select m from Account m where SHA2(AES_ENCRYPT(:plainUserId, concat('ONZ!@#', m.gubn, m.snsType)),512) = m.userId";
        Account result = null;
        try{
            result = em.createQuery(query, Account.class)
                    //.setParameter("plainUserId", plainUserId).getResultStream().findFirst().orElse(null);
                    .setParameter("plainUserId", plainUserId).getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
        }


        Optional<Account> optionalAccount = Optional.ofNullable(result);
        return optionalAccount;
    }

    @Override
    public Optional<Account> findByPlainUserId3(String plainUserId) {
        QAccount m = new QAccount("m");

        BooleanBuilder where = new BooleanBuilder();
        SimpleTemplate st = Expressions.template(String.class, "SHA2(AES_ENCRYPT({0}, concat('ONZ!@#', m.gubn, m.snsType)),512)", plainUserId);
        where.and(st.eq(m.userId));
//        where.and(m.isDelete.eq(YN.N));
        Account findAccount = qf
                .select(m)
                .from(m)
                .where(where)
                .fetchOne();

        Optional<Account> optionalAccount = Optional.ofNullable(findAccount);
        return optionalAccount;
    }

    @Override
    @Deprecated
    public Optional<Account> findByEncodedUserId(String encodedUserId) {
        // QueryDSL
        QAccount account = QAccount.account;

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);

        // ?????? ?????????(????????? ????????? ?????????)
        Root<Account> a = query.from(Account.class);

        // ????????????
        CriteriaQuery<Account> cq =
                query.select(a).where(cb.equal(a.get("userId"), encodedUserId));

        Account result = em.createQuery(cq).getSingleResult();
        Optional<Account> optionalAccount = Optional.of(result);
        return optionalAccount;
    }

    @Override
    public Optional<Account> findByEncodedUserId2(String encodedUserId) {
        // QueryDSL
        QAccount account = QAccount.account;

        // ????????????
        BooleanBuilder where = new BooleanBuilder();
        where.and(account.userId.eq(encodedUserId));

        // ????????????
        Account findedAccount = qf.selectFrom(account)
                                .where(where)
                                .fetchOne();

        return Optional.ofNullable(findedAccount);
    }


}
