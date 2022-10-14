package com.onz.modules.admin.reviews.infra;

import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.QAccount;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberRequestDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseDto;
import com.onz.modules.admin.reviews.web.dto.ReviewMRequestDto;
import com.onz.modules.admin.reviews.web.dto.ReviewMallResponseDto;
import com.onz.modules.admin.reviews.web.dto.ReviewsResponseDto;
import com.onz.modules.company.domain.QCompany;
import com.onz.modules.counsel.domain.QCounsel;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.review.domain.QCompanyReview;
import com.onz.modules.review.domain.QInterviewReview;
import com.onz.modules.review.domain.QYearAmtReview;
import com.onz.modules.review.domain.dto.ReviewAllDto;
import com.onz.modules.review.web.dto.FindEstaRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onz.modules.admin.reviews.infra.ReviewMRepository.*;
import static com.onz.modules.review.infra.ReviewRepository.*;

public class ReviewMRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        ReviewMRepositoryExtension {

    private final JPAQueryFactory qf;
    private final EntityManager em;

    public ReviewMRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Account.class);
        this.qf = qf;
        this.em = em;
    }

    public List<ReviewMallResponseDto> findByAllReview(Pageable pageable) {
        //FIND_ALL_IDS
//        String query = FIND_ALL_IDS;
        // 기관리뷰
        String query1 = FIND_ONE_ID;
        query1 += "";
        // 인터뷰리뷰
        String query2 = FIND_TWO_ID;
        String where2 = "";
        query2 += where2;
        // 연봉리뷰
        String query3 = FIND_THR_ID;
        String where3 = "";
        query3 += where3;
        String query = query1 + " UNION ALL " + query2 + " UNION ALL " + query3;
        //        String query = query1;
        Query nativequery = em
                .createNativeQuery(query, "reviewAdminUnion");
        List<ReviewMallResponseDto> resultList = nativequery.getResultList();
        return resultList;
    }

    public List<ReviewsResponseDto> findByCompanyReview(Pageable pageable) {
        QAccount account = QAccount.account;
        QCompany company = QCompany.company;
        QCompanyReview companyReview = QCompanyReview.companyReview;

        // where절 정의
        // 쿼리 생성(리스트)
        JPQLQuery<ReviewsResponseDto> result = from(companyReview).select(
                        Projections.fields(ReviewsResponseDto.class,
                                Expressions.asString("COMPANY").as("type"),
                                companyReview.id,
                                companyReview.state,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(company.interestCompany)
                                                .from(company)
                                                .where(company.id.eq(companyReview.company.id))
                                        , "interestCompany"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(company.establishmentType)
                                                .from(company)
                                                .where(company.id.eq(companyReview.company.id))
                                        , "establishmentType"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(company.officeName)
                                                .from(company)
                                                .where(company.id.eq(companyReview.company.id))
                                        , "companyName"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(account.userId)
                                                .from(account)
                                                .where(account.id.eq(companyReview.account.id))
                                        , "userId"),
                                companyReview.createdAt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(company.zonecode)
                                                .from(company)
                                                .where(company.id.eq(companyReview.company.id))
                                        , "zonecode")
                        )
                )
                .where(companyReview.state.eq(State.W));
        JPQLQuery<ReviewsResponseDto> query = getQuerydsl().applyPagination(pageable, result);
//        JPQLQuery<Account> result = from(account).where(where);

        QueryResults<ReviewsResponseDto> findCompanyReviewResults = query.fetchResults();
        List<ReviewsResponseDto> findCompanyReviewResultsResults = findCompanyReviewResults.getResults();


        return findCompanyReviewResultsResults;
    }

    public List<ReviewsResponseDto> findByInterviewReview(Pageable pageable) {
        QAccount account = QAccount.account;
        QCompany company = QCompany.company;
        QInterviewReview interviewReview= QInterviewReview.interviewReview;

        // where절 정의
        // 쿼리 생성(리스트)
        JPQLQuery<ReviewsResponseDto> result = from(interviewReview).select(
                        Projections.fields(ReviewsResponseDto.class,
                                Expressions.asString("INTERVIEW").as("type"),
                                interviewReview.id,
                                interviewReview.state,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(company.interestCompany)
                                                .from(company)
                                                .where(company.id.eq(interviewReview.company.id))
                                        , "interestCompany"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(company.establishmentType)
                                                .from(company)
                                                .where(company.id.eq(interviewReview.company.id))
                                        , "establishmentType"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(company.officeName)
                                                .from(company)
                                                .where(company.id.eq(interviewReview.company.id))
                                        , "companyName"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(account.userId)
                                                .from(account)
                                                .where(account.id.eq(interviewReview.account.id))
                                        , "userId"),
                                interviewReview.createdAt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(company.zonecode)
                                                .from(company)
                                                .where(company.id.eq(interviewReview.company.id))
                                        , "zonecode")
                        )
                )
                .where(interviewReview.state.eq(State.W));
        JPQLQuery<ReviewsResponseDto> query = getQuerydsl().applyPagination(pageable, result);
//        JPQLQuery<Account> result = from(account).where(where);

        QueryResults<ReviewsResponseDto> findCompanyReviewResults = query.fetchResults();
        List<ReviewsResponseDto> findCompanyReviewResultsResults = findCompanyReviewResults.getResults();


        return findCompanyReviewResultsResults;
    }

    public List<ReviewsResponseDto> findByAmtReview(Pageable pageable) {
        QAccount account = QAccount.account;
        QCompany company = QCompany.company;
        QYearAmtReview amtReview= QYearAmtReview.yearAmtReview;

        // where절 정의
        // 쿼리 생성(리스트)
        JPQLQuery<ReviewsResponseDto> result = from(amtReview).select(
                        Projections.fields(ReviewsResponseDto.class,
                                Expressions.asString("AMT").as("type"),
                                amtReview.id,
                                amtReview.state,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(company.interestCompany)
                                                .from(company)
                                                .where(company.id.eq(amtReview.company.id))
                                        , "interestCompany"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(company.establishmentType)
                                                .from(company)
                                                .where(company.id.eq(amtReview.company.id))
                                        , "establishmentType"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(company.officeName)
                                                .from(company)
                                                .where(company.id.eq(amtReview.company.id))
                                        , "companyName"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(account.userId)
                                                .from(account)
                                                .where(account.id.eq(amtReview.account.id))
                                        , "userId"),
                                amtReview.createdAt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(company.zonecode)
                                                .from(company)
                                                .where(company.id.eq(amtReview.company.id))
                                        , "zonecode")
                        )
                )
                .where(amtReview.state.eq(State.W));
        JPQLQuery<ReviewsResponseDto> query = getQuerydsl().applyPagination(pageable, result);
//        JPQLQuery<Account> result = from(account).where(where);

        QueryResults<ReviewsResponseDto> findCompanyReviewResults = query.fetchResults();
        List<ReviewsResponseDto> findCompanyReviewResultsResults = findCompanyReviewResults.getResults();


        return findCompanyReviewResultsResults;
    }
}
