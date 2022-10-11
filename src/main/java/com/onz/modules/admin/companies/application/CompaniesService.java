package com.onz.modules.admin.companies.application;

import com.onz.common.exception.CustomException;
import com.onz.modules.admin.companies.infra.CompaniesRepository;
import com.onz.modules.admin.companies.web.dto.CompaniesRequestDto;
import com.onz.modules.admin.companies.web.dto.CompaniesResponseDto;
import com.onz.modules.admin.member.livemember.infra.LiveMemberRepository;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberRequestDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseWrapDto;
import com.onz.modules.company.application.util.AggregateCompany;
import com.onz.modules.company.application.util.dto.EvaluationScore;
import com.onz.modules.company.application.util.dto.UserScore;
import com.onz.modules.company.domain.Company;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.infra.CompanyReviewRepository;
import com.querydsl.jpa.JPQLQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CompaniesService {

    private final CompaniesRepository companiesRepository;
    private final CompanyReviewRepository companyReviewRepository;

    public List<CompaniesResponseDto> companiesSearch(HttpServletResponse response, CompaniesRequestDto companiesRequestDto, Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<CompaniesResponseDto> companiesSearchListResult = companiesRepository.findByCompanies(companiesRequestDto, pageable);
        List<CompaniesResponseDto> companiesSearchListResultAddJipyoScore = companiesSearchListResult.stream().map(companiesResponseDto -> {
            Company company = companiesRepository.findById(companiesResponseDto.getId()).orElse(null);
            if(company != null){
                List<CompanyReview> reviews = companyReviewRepository.listCompanyReviewByCompanyId(companiesResponseDto.getId());
                AggregateCompany agg = reviews.stream().collect(AggregateCompany::new, AggregateCompany::add, AggregateCompany::merge);
                EvaluationScore escore = new EvaluationScore(company);
                UserScore uscore = new UserScore(agg);
                long evalTot = escore.getScoreTot();
                long userTot = uscore.getScoreTot();
                int avgCnt = 0;
                if(evalTot>0) avgCnt++;
                if(userTot>0) avgCnt++;
                Long jipyoScore = avgCnt!=0 ? (evalTot + userTot) / avgCnt : 0;
                companiesResponseDto.setJipyoScore(jipyoScore);
            }
            return companiesResponseDto;
        }).collect(Collectors.toList());
//        List<LiveMemberResponseDto> result = liveMemberResponseDtos.stream().map(e -> {
//            LiveMemberResponseDto rs = new LiveMemberResponseDto(e);
//            // 기관리뷰 개수
//            Long companyReviewCnt = companyReviewRepository.countByAccount_Id(e.getId());
//            rs.setCompanyReviewCnt(companyReviewCnt);
//            // 인터뷰리뷰 개수
//            Long interviewReviewCnt = interviewReviewRepository.countByAccount_Id(e.getId());
//            rs.setInterviewReviewCnt(interviewReviewCnt);
//            // ...
//            // 연봉리뷰 개수
//            Long amtReviewCnt = amtReviewRepository.countByAccount_Id(e.getId());
//            rs.setAmtReviewCnt(amtReviewCnt);
//            // ...
//            // 상담리뷰 개수
//            Long counselQCnt = counselRepository.countByAccount_IdAndQnaGubn(e.getId(), QnaGubn.Q);
//            rs.setCounselQCnt(counselQCnt);
//            // ...
//            Long counselACnt = counselRepository.countByAccount_IdAndQnaGubn(e.getId(),QnaGubn.A);
//            rs.setCounselACnt(counselACnt);
//            return rs;
//        }).collect(Collectors.toList());
        return companiesSearchListResultAddJipyoScore;
    }
}
