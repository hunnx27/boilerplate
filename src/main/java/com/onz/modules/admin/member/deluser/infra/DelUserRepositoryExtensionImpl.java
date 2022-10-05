package com.onz.modules.admin.member.deluser.infra;


import com.onz.common.enums.Role;
import com.onz.common.enums.YN;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.QAccount;
import com.onz.modules.admin.member.deluser.web.dto.DelUserListResponseDto;
import com.onz.modules.admin.member.deluser.web.dto.DelUserRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.onz.common.enums.YN.*;

public class DelUserRepositoryExtensionImpl extends QuerydslRepositorySupport implements DelUserRepositoryExtension {
    private final JPAQueryFactory qf;
    private final EntityManager em;

    public DelUserRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Account.class);
        this.qf = qf;
        this.em = em;
    }

    private BooleanBuilder getWhere(DelUserRequestDto delUserRequestDto, QAccount account) {

        BooleanBuilder where = new BooleanBuilder();
        if (delUserRequestDto.getGubn() == null) {
            //gubun은 이넘으로 all을 받기 떄문에 처리 ㄴㄴ -> 해야할듯
        } else {
            where.and(account.gubn.eq(delUserRequestDto.getGubn()));
        }
        if (delUserRequestDto.getDeletedAtA() != null) {
            if (delUserRequestDto.getDeletedAtB() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                String ccc = String.format(account.createdAt.toString(), "yyyy-MM-dd");
                ZonedDateTime aaa = ZonedDateTime.of(LocalDateTime.parse(delUserRequestDto.getDeletedAtA() + " 00:00:00", formatter), ZoneId.of("Asia/Seoul"));
                ZonedDateTime bbb = ZonedDateTime.of(LocalDateTime.parse(delUserRequestDto.getDeletedAtB() + " 23:59:59", formatter), ZoneId.of("Asia/Seoul"));
                where.and(account.deletedAt.between(Expressions.dateTemplate(ZonedDateTime.class, "{0}", aaa), Expressions.dateTemplate(ZonedDateTime.class, "{0}", bbb)));
            }
        }
        if (delUserRequestDto.getUserId() != null) {
            where.and(account.userId.eq(delUserRequestDto.getUserId()));
        }
        where.and(account.role.eq(Role.USER));
        where.and(account.isDelete.eq(Y));
        return where;
    }

    @Override
    public List<DelUserListResponseDto> findByDelUser(DelUserRequestDto delUserRequestDto, Pageable pageable) {
        // Q클래스 정의
        QAccount account = QAccount.account;

        // where절 정의
        BooleanBuilder where = this.getWhere(delUserRequestDto, account);
        // 쿼리 생성(리스트)
        JPQLQuery<DelUserListResponseDto> result = from(account).select(
                Projections.fields(DelUserListResponseDto.class,
                        account.id,
                        account.gubn,
                        account.userId,
                        account.snsType,
                        account.createdAt,
                        account.deletedAt)).where(where);
        JPQLQuery<DelUserListResponseDto> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<DelUserListResponseDto> findByDelUser = query.fetchResults();
        List<DelUserListResponseDto> findLiveMemberListResults = findByDelUser.getResults();

        return findLiveMemberListResults;
    }

}