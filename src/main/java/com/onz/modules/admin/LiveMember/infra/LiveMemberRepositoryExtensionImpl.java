package com.onz.modules.admin.LiveMember.infra;

import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.QAccount;
import com.onz.modules.admin.LiveMember.domain.LiveMemberRequestDto;
import com.onz.modules.admin.LiveMember.domain.LiveMemberResponseDto;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.counsel.domain.QCounsel;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.review.domain.QCompanyReview;
import com.onz.modules.review.domain.QInterviewReview;
import com.onz.modules.review.domain.QYearAmtReview;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.onz.modules.company.domain.QCompany.company;
import static com.querydsl.core.types.ExpressionUtils.in;

@Repository
@Slf4j
public class LiveMemberRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        LiveMemberRepositoryExtension {
    private final JPAQueryFactory qf;
    private final EntityManager em;

    public LiveMemberRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Account.class);
        this.qf = qf;
        this.em = em;
    }

    public List<LiveMemberResponseDto> findByLiveMember(LiveMemberRequestDto liveMemberRequestDto) {
        QAccount account = QAccount.account;
        BooleanBuilder where = new BooleanBuilder();
        String zoneCode = liveMemberRequestDto.getSido() + liveMemberRequestDto.getSigungu();
        if (liveMemberRequestDto.getSido() != null) {
            //sido가 널이 아닐떄
            if (liveMemberRequestDto.getSido() == null) {
                //모두검색
            }
            if (liveMemberRequestDto.getSigungu() == null) {//Sido값은들어옴 ,만약 군구가 널이라면
                where.and(QAccount.account.myinfo.interestZone.startsWith(liveMemberRequestDto.getSido()));//시도로검색
            } else {
                //만약 null이 아니라면
                log.info(String.valueOf(account.myinfo.interestZone));
                where.and((QAccount.account.myinfo.interestZone.eq(zoneCode)));
            }
        }
        if (liveMemberRequestDto.getGubn() == null) {
            //gubun은 이넘으로 all을 받기 떄문에 처리 ㄴㄴ -> 해야할듯
        } else {
            where.and(account.gubn.eq(liveMemberRequestDto.getGubn()));
        }
        if (liveMemberRequestDto.getBirthYYYYo() != null) {
            if (liveMemberRequestDto.getBirthYYYYt() != null) {
//                Long one = Long.valueOf(liveMemberRequestDto.getBirthYYYYo());
//                Long two = Long.valueOf(liveMemberRequestDto.getBirthYYYYt());
                where.and(account.myinfo.birthYYYY.between(liveMemberRequestDto.getBirthYYYYo(), liveMemberRequestDto.getBirthYYYYt()));
            }
        }
        if (liveMemberRequestDto.getCreateAtA() != null) {
            if (liveMemberRequestDto.getCreateAtD() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                String ccc = String.format(account.createdAt.toString(), "yyyy-MM-dd");
                ZonedDateTime aaa = ZonedDateTime.of(LocalDateTime.parse(liveMemberRequestDto.getCreateAtA() + " 00:00:00", formatter), ZoneId.of("Asia/Seoul"));
                ZonedDateTime bbb = ZonedDateTime.of(LocalDateTime.parse(liveMemberRequestDto.getCreateAtD() + " 23:59:59", formatter), ZoneId.of("Asia/Seoul"));
                where.and(account.createdAt.between(
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", aaa),
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", bbb)
                ));


            }
        }
        if (liveMemberRequestDto.getUserId() != null) {
            where.and(account.userId.eq(liveMemberRequestDto.getUserId()));
        }
        QCompanyReview companyReview = QCompanyReview.companyReview;
        QInterviewReview interviewReview = QInterviewReview.interviewReview;
        QYearAmtReview amtReview = QYearAmtReview.yearAmtReview;
        QCounsel counsel = QCounsel.counsel;

        if (liveMemberRequestDto.getReviewCount() != null) {
            if (!liveMemberRequestDto.getReviewCount().equals("N")) {
                if (liveMemberRequestDto.getOptions().equals("goe")) {
                    where.and(
                            Expressions.asNumber(
                                            JPAExpressions
                                                    .select(companyReview.count())
                                                    .from(companyReview)
                                                    .where(companyReview.account.eq(account))
                                    ).castToNum(Long.class)
                                    .add(
                                            Expressions.asNumber(
                                                    JPAExpressions
                                                            .select(interviewReview.count())
                                                            .from(interviewReview)
                                                            .where(interviewReview.account.eq(account))
                                            ).castToNum(Long.class)
                                    )
                                    .add(
                                            Expressions.asNumber(
                                                    JPAExpressions
                                                            .select(amtReview.count())
                                                            .from(amtReview)
                                                            .where(amtReview.account.eq(account))
                                            ).castToNum(Long.class)
                                    ).goe(liveMemberRequestDto.getReviewCountNum()));
                } else {
                    where.and(
                            JPAExpressions
                                    .select(companyReview.count())
                                    .from(companyReview)
                                    .where(companyReview.account.eq(account))
                                    .loe(liveMemberRequestDto.getReviewCountNum()));
                }
            }
        }

        if (liveMemberRequestDto.getCounselQCount() != null) {
            if (!liveMemberRequestDto.getCounselQCount().equals("N")) {
                if (liveMemberRequestDto.getOptions().equals("goe")) {
                    where.and(
                            JPAExpressions
                                    .select(counsel.count())
                                    .from(counsel)
                                    .where(
                                            counsel.account.eq(account).and(counsel.qnaGubn.eq(QnaGubn.Q))
                                    )
                                    .goe(liveMemberRequestDto.getCounselQCountNum()));
                } else {
                    where.and(
                            JPAExpressions
                                    .select(counsel.count())
                                    .from(counsel)
                                    .where(counsel.account.eq(account).and(counsel.qnaGubn.eq(QnaGubn.Q)))
                                    .loe(liveMemberRequestDto.getCounselQCountNum()));
                }
            }
        }

        if (liveMemberRequestDto.getCounselACount() != null) {
            if (!liveMemberRequestDto.getCounselACount().equals("N")) {
                if (liveMemberRequestDto.getOptions().equals("goe")) {
                    where.and(
                            JPAExpressions
                                    .select((counsel.qnaGubn.eq(QnaGubn.A)).count())
                                    .from(counsel)
                                    .where(counsel.account.eq(account))
                                    .goe(liveMemberRequestDto.getCounselACountNum()));
                } else {
                    where.and(
                            JPAExpressions
                                    .select((counsel.qnaGubn.eq(QnaGubn.A)).count())
                                    .from(counsel)
                                    .where(counsel.account.eq(account))
                                    .loe(liveMemberRequestDto.getCounselACountNum()));
                }
            }
        }
        //goe 이상 loe 이하

        JPQLQuery<LiveMemberResponseDto> result = from(account).select(
                        Projections.fields(LiveMemberResponseDto.class,
                        account.id,
                        account.gubn,
                        account.userId,
                        account.snsType,
                        Expressions.asString("").as("rank"),
                        account.point,
                        account.createdAt,
                        ExpressionUtils.as(
                                JPAExpressions
                                    .select(companyReview.count())
                                    .from(companyReview)
                                    .where(companyReview.account.eq(account))
                            ,"companyReviewCnt"),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(interviewReview.count())
                                        .from(interviewReview)
                                        .where(interviewReview.account.eq(account))
                                ,"interviewReviewCnt"),
                        ExpressionUtils.as(
                                JPAExpressions
                                    .select(amtReview.count())
                                    .from(amtReview)
                                    .where(amtReview.account.eq(account))
                                ,"amtReviewCnt"),
                        ExpressionUtils.as(
                                JPAExpressions
                                    .select(counsel.count())
                                    .from(counsel)
                                    .where(
                                            counsel.account.eq(account).and(counsel.qnaGubn.eq(QnaGubn.Q))
                                    )
                                ,"counselQCnt"),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(counsel.count())
                                        .from(counsel)
                                        .where(
                                                counsel.account.eq(account).and(counsel.qnaGubn.eq(QnaGubn.A))
                                        )
                            , "counselACnt")
                    )
                )
                .where(where);

//        JPQLQuery<Account> result = from(account).where(where);
        QueryResults<LiveMemberResponseDto> fetchResults = result.fetchResults();
        List<LiveMemberResponseDto> list = fetchResults.getResults();
        return list;
        //return list.stream().map(com -> new LiveMemberResponseDto(com)).collect(Collectors.toList());

        /*
        JPQLQuery<YearAmtReview> result = from(qYearAmtReview).where(where);
        JPQLQuery<YearAmtReview> query = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, result);
        QueryResults<YearAmtReview> fetchResults = query.fetchResults();

        List<YearAmtReview> list = fetchResults.getResults();
        return list.stream().map(com -> new YearAmtListResponseDto(com)).collect(Collectors.toList());
         */
    }

}
