package com.onz.modules.admin.companies.infra;

import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.companies.web.dto.CompaniesRequestDto;
import com.onz.modules.admin.companies.web.dto.CompaniesResponseDto;
import com.onz.modules.company.application.util.AggregateCompany;
import com.onz.modules.company.domain.QCompany;
import com.onz.modules.company.web.dto.reponse.CompanyJipyoResponse;
import com.onz.modules.review.domain.CompanyReview;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class CompaniesRepositoryExtensionImpl extends QuerydslRepositorySupport implements CompaniesRepositoryExtension {
    private final JPAQueryFactory qf;
    private final EntityManager em;

    public CompaniesRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Account.class);
        this.qf = qf;
        this.em = em;
    }

    private BooleanBuilder getWhere(CompaniesRequestDto companiesRequestDto,
                                    QCompany company,QCompanyReview companyReview,QInterviewReview interviewReview,QYearAmtReview amtReview) {

        BooleanBuilder where = new BooleanBuilder();
        String zoneCode = companiesRequestDto.getSiDo() + companiesRequestDto.getSigunGu();
        if (companiesRequestDto.getSiDo() != null) {
            //sido가 널이 아닐떄
            if (companiesRequestDto.getSiDo() == null) {
                //모두검색
            }
            if (companiesRequestDto.getSigunGu() == null) {//Sido값은들어옴 ,만약 군구가 널이라면
                where.and(company.zonecode.startsWith(companiesRequestDto.getSiDo()));//시도로검색
            } else {
                //만약 null이 아니라면
                where.and((company.zonecode.eq(zoneCode)));
            }
        }
        if (companiesRequestDto.getEstablishmentType() == null) {
            //gubun은 이넘으로 all을 받기 떄문에 처리 ㄴㄴ -> 해야할듯
        } else {
            where.and(company.establishmentType.eq(companiesRequestDto.getEstablishmentType()));
        }
        if (companiesRequestDto.getCreateAtA() != null) {
            if (companiesRequestDto.getCreateAtD() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                String ccc = String.format(account.createdAt.toString(), "yyyy-MM-dd");
                ZonedDateTime aaa = ZonedDateTime.of(LocalDateTime.parse(companiesRequestDto.getCreateAtA() + " 00:00:00", formatter), ZoneId.of("Asia/Seoul"));
                ZonedDateTime bbb = ZonedDateTime.of(LocalDateTime.parse(companiesRequestDto.getCreateAtD() + " 23:59:59", formatter), ZoneId.of("Asia/Seoul"));
                where.and(company.createdAt.between(
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", aaa),
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", bbb)
                ));


            }
        }
        if (companiesRequestDto.getCompanyCh() != null) {
            if (!companiesRequestDto.getCompanyCh().equals("N")) {
                where.and(company.officeName.eq(companiesRequestDto.getCompanyNameCh()));
            }
        }

        if (companiesRequestDto.getReviewCountCheck() != null) {
            if (!companiesRequestDto.getReviewCountCheck().equals("N")) {
                if (companiesRequestDto.getOptions().equals("goe")) {
                    where.and(
                            Expressions.asNumber(
                                            JPAExpressions
                                                    .select(companyReview.count())
                                                    .from(companyReview)
                                                    .where(companyReview.company.eq(company))
                                    ).castToNum(Long.class)
                                    .add(
                                            Expressions.asNumber(
                                                    JPAExpressions
                                                            .select(interviewReview.count())
                                                            .from(interviewReview)
                                                            .where(interviewReview.company.eq(company))
                                            ).castToNum(Long.class)
                                    )
                                    .add(
                                            Expressions.asNumber(
                                                    JPAExpressions
                                                            .select(amtReview.count())
                                                            .from(amtReview)
                                                            .where(amtReview.company.eq(company))
                                            ).castToNum(Long.class)
                                    ).goe(companiesRequestDto.getReviewCountNum()));
                } else {
                    where.and(
                            JPAExpressions
                                    .select(companyReview.count())
                                    .from(companyReview)
                                    .where(companyReview.company.eq(company))
                                    .loe(companiesRequestDto.getReviewCountNum()));
                }
            }
        }
        return where;
    }

    @Override
    public List<CompaniesResponseDto> findByCompanies(CompaniesRequestDto companiesRequestDto, Pageable pageable) {
        // Q클래스 정의
        QCompanyReview companyReview = QCompanyReview.companyReview;
        QInterviewReview interviewReview = QInterviewReview.interviewReview;
        QYearAmtReview amtReview = QYearAmtReview.yearAmtReview;
        QCompany company =QCompany.company;


        // where절 정의
        BooleanBuilder where = this.getWhere(companiesRequestDto, company, companyReview, interviewReview, amtReview);

        // 쿼리 생성(리스트)
        JPQLQuery<CompaniesResponseDto> result = from(company).select(
                        Projections.fields(CompaniesResponseDto.class,
                                company.id,
                                company.interestCompany,
                                company.establishmentType,
                                company.officeName,
                                company.zonecode,
                                company.createdAt,
                                company.modifiedAt,
                                company.isDelete,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(companyReview.count())
                                                .from(companyReview)
                                                .where(companyReview.company.eq(company))
                                        , "companyReviewCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(interviewReview.count())
                                                .from(interviewReview)
                                                .where(interviewReview.company.eq(company))
                                        , "interviewReviewCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(amtReview.count())
                                                .from(amtReview)
                                                .where(amtReview.company.eq(company))
                                        , "amtReviewCnt")
                        )
                )
                .where(where);
        JPQLQuery<CompaniesResponseDto> query = getQuerydsl().applyPagination(pageable, result);

        QueryResults<CompaniesResponseDto> findLiveMemberResults = query.fetchResults();
        List<CompaniesResponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();

        return findLiveMemberListResults;
    }
}
