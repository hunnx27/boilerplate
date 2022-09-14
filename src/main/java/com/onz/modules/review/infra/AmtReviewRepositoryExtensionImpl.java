package com.onz.modules.review.infra;

import com.onz.modules.company.domain.Company;
import com.onz.modules.company.web.dto.reponse.InterviewListResponseDto;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.QYearAmtReview;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.company.web.dto.reponse.YearAmtListResponseDto;
import com.onz.modules.review.web.dto.FindEstaRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AmtReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements AmtReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public AmtReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(YearAmtReview.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<YearAmtListResponseDto> ListAmt(FindEstaRequestDto findEstaRequestDto, Pageable pageable) {
        QYearAmtReview qYearAmtReview = QYearAmtReview.yearAmtReview;
        BooleanBuilder where = new BooleanBuilder();
        if (qYearAmtReview.company.zonecode != null) {
            if (qYearAmtReview.company.zonecode.toString().length() == 4) {
                where.and(qYearAmtReview.company.zonecode.eq(findEstaRequestDto.getSido()));
                where.and(qYearAmtReview.company.zonecode.eq(findEstaRequestDto.getSigungu()));
            } else if (qYearAmtReview.company.zonecode.toString().length() < 3) {
                where.and(qYearAmtReview.company.zonecode.startsWith(findEstaRequestDto.getSido()));
            }
        }
        if (qYearAmtReview.company.establishmentType != null) {
            where.and(qYearAmtReview.company.establishmentType.eq(findEstaRequestDto.getEstablishmentType()));
        }
        if (qYearAmtReview.company.interestCompany != null) {
            where.and(qYearAmtReview.company.interestCompany.eq(findEstaRequestDto.getInterestCompany()));
        }

        JPQLQuery<YearAmtReview> result = from(qYearAmtReview).where(where);
        JPQLQuery<YearAmtReview> query = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, result);
        QueryResults<YearAmtReview> fetchResults = query.fetchResults();

        List<YearAmtReview> list = fetchResults.getResults();
        return list.stream().map(com -> new YearAmtListResponseDto(com)).collect(Collectors.toList());
    }

}

//    JPQLQuery<Company> query = getQuerydsl().applyPagination(pageable, result);
//