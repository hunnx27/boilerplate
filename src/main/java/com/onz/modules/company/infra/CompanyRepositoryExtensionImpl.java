package com.onz.modules.company.infra;

import com.onz.common.enums.YN;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.QAccount;
import com.onz.modules.common.address.domain.Address;
import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.web.dto.AddressResponse;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.domain.QCompany;
import com.onz.modules.company.web.dto.reponse.CompanySearchResponse;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.onz.modules.company.domain.QCompany.company;

public class CompanyRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        CompanyRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;
    private AddressRepository addressRepository;

    public CompanyRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Company.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public PageImpl<Company> list(CompanySearchRequest searchRequest) {
        QCompany CompanySearchResponse = QCompany.company;

        final Pageable pageable = searchRequest.getPageable();

        BooleanBuilder where = new BooleanBuilder();
        where.and(company.isDelete.eq(YN.N));

        JPQLQuery<Company> result = from(company)
            .where(where);

        JPQLQuery<Company> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<Company> fetchResults = query.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

    @Override
    public List<CompanySearchResponse> search(CompanySearchRequest companySearchRequest) {
        BooleanBuilder builder = new BooleanBuilder();
        if (companySearchRequest.getName() != null) {
            builder.and(company.officeName.contains(companySearchRequest.getName()));
        }
        if (companySearchRequest.getCode() !=null) {
            if (companySearchRequest.getCode().length()<3) {
//                String first_request = companySearchRequest.getCode();
//                builder.and(company.zonecode.substring(0,2).eq(first_request));
                String first_request = companySearchRequest.getCode().substring(0,2);//시
                builder.and(company.zonecode.startsWith(first_request));
            }else{
                builder.and(company.zonecode.eq(companySearchRequest.getCode()));
            }

        }
        return jpaQueryFactory.selectFrom(company).where(builder).select(Projections.constructor(CompanySearchResponse.class,company.officeName,company.zonecode,company.id,company.establishmentType)).fetch();
    }
//        QAccount account = QAccount.account;
//
//        // 조건생성
//        BooleanBuilder where = new BooleanBuilder();
//        where.and(account.userId.eq(encodedUserId));
//
//        // 쿼리생성
//        Account findedAccount = qf.selectFrom(account)
//                .where(where)
//                .fetchOne();
//
//        return Optional.ofNullable(findedAccount);
//    }


    @Override
    public void update(CompanyUpdateRequest updateRequest) {
//        QCompany company = QCompany.company;
//        update(company)
//            .set(company.officeName, updateRequest.getName())
//            .where(company.id.eq(updateRequest.getId()))
//            .execute();
    }
}
