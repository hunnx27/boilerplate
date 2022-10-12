package com.onz.modules.admin.companies.application;

import com.onz.common.exception.CustomException;
import com.onz.modules.admin.companies.infra.CompaniesRepository;
import com.onz.modules.admin.companies.web.dto.*;
import com.onz.modules.company.application.util.AggregateCompany;
import com.onz.modules.company.application.util.dto.EvaluationScore;
import com.onz.modules.company.application.util.dto.UserScore;
import com.onz.modules.company.domain.Company;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.infra.CompanyReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
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

    public CompaniesDetailResponseDto companiesDetail(HttpServletResponse response, @PathVariable Long id) {
        CompaniesDetailResponseDto companiesDetailResponseDto = companiesRepository.findByCompaniesDetail(id);
        Company company = companiesRepository.findById(companiesDetailResponseDto.getId()).orElse(null);
        if (company != null) {
            List<CompanyReview> reviews = companyReviewRepository.listCompanyReviewByCompanyId(companiesDetailResponseDto.getId());
            AggregateCompany agg = reviews.stream().collect(AggregateCompany::new, AggregateCompany::add, AggregateCompany::merge);
            EvaluationScore escore = new EvaluationScore(company);
            UserScore uscore = new UserScore(agg);
            long evalTot = escore.getScoreTot();
            long userTot = uscore.getScoreTot();
            EvaluationScore evaluationScore = EvaluationScore.builder()
                        .scoreTot(escore.getScoreTot())
                        .scoreCareEnv(escore.getScoreCareEnv())
                        .scoreOprManage(escore.getScoreOprManage())
                        .scoreCareCourse(escore.getScoreCareCourse())
                        .scoreTeach(escore.getScoreTeach())
                        .scoreHealth(escore.getScoreHealth())
                        .scoreSafty(escore.getScoreSafty())
                        .build();
            companiesDetailResponseDto.setEvaluationScore(evaluationScore);
            int avgCnt = 0;
            if (evalTot > 0) avgCnt++;
            if (userTot > 0) avgCnt++;
            Long jipyoScore = avgCnt != 0 ? (evalTot + userTot) / avgCnt : 0;

            if (company.getAgePeoples() != null && !"".equals(company.getAgePeoples())) {
                String[] one = company.getAgePeoples().split(",");
                Child child = Child.builder()
                        .zeroAge(Long.parseLong(one.length > 1 ? one[0] : "0"))
                        .oneAge(Long.parseLong(one.length > 1 ? one[1] : "0"))
                        .twoAge(Long.parseLong(one.length > 1 ? one[2] : "0"))
                        .threeAge(Long.parseLong(one.length > 1 ? one[3] : "0"))
                        .forAge(Long.parseLong(one.length > 1 ? one[4] : "0"))
                        .fiveAge(Long.parseLong(one.length > 1 ? one[5] : "0"))
                        .infantMix(Long.parseLong(one.length > 1 ? one[6] : "0"))
                        .childMix(Long.parseLong(one.length > 1 ? one[7] : "0"))
                        .disabled(Long.parseLong(one.length > 1 ? one[8] : "0"))
                        .build();
                companiesDetailResponseDto.setChild(child);
//                mpas.put("zeroage", one.length>1 ? one[0] : "0");
//                mpas.put("oneage", one.length>2 ? one[1] : "0");
//                mpas.put("oneage", one.length>2 ? one[1] : "0");
//                mpas.put("oneage", one.length>2 ? one[1] : "0");
//                mpas.put("oneage", one.length>2 ? one[1] : "0");
//                mpas.put("oneage", one.length>2 ? one[1] : "0");
//                mpas.put("oneage", one.length>2 ? one[1] : "0");
            }
            if(company.getPerItems() !=null && !"".equals(company.getPerItems())){
                String[] two = company.getPerItems().split(",");
                Staff staff = Staff.builder()
                        .total(Long.parseLong(two.length > 1 ? two[0] : "0"))
                        .director(Long.parseLong(two.length > 1 ? two[1] : "0"))
                        .daycareT(Long.parseLong(two.length > 1 ? two[2] : "0"))
                        .specialT(Long.parseLong(two.length > 1 ? two[3] : "0"))
                        .healerT(Long.parseLong(two.length > 1 ? two[4] : "0"))
                        .dietitianT(Long.parseLong(two.length > 1 ? two[5] : "0"))
                        .nurseT(Long.parseLong(two.length > 1 ? two[6] : "0"))
                        .nursingAide(Long.parseLong(two.length > 1 ? two[7] : "0"))
                        .cook(Long.parseLong(two.length > 1 ? two[8] : "0"))
                        .officeWorker(Long.parseLong(two.length > 1 ? two[9] : "0"))
                        .build();
                companiesDetailResponseDto.setStaff(staff);
            }
            companiesDetailResponseDto.setJipyoScore(jipyoScore);
        }
        
        return companiesDetailResponseDto;
    }


    public CompaniesDetailReviewDto companiesDetailReview(HttpServletResponse response, @PathVariable Long id) {
        CompaniesDetailReviewDto companiesDetailReviewDto = companiesRepository.findByCompaniesDetailReview(id);
        long totalCnt=0;
        totalCnt=companiesDetailReviewDto.getReviewCnt()+ companiesDetailReviewDto.getAmtCnt()+ companiesDetailReviewDto.getInterviewCnt();
        companiesDetailReviewDto.setTotalCnt(totalCnt);
        return companiesDetailReviewDto;
    }

    public CompaniesDetailJipyoDto companiesDetailJipyo(HttpServletResponse response, @PathVariable Long id) {
        CompaniesDetailJipyoDto companiesDetailJipyoDto = companiesRepository.findByCompaniesDtailJipyo(id);
//        =scoreA+scoreT/2;
        Company company = companiesRepository.findById(companiesDetailJipyoDto.getId()).orElse(null);
        if (company != null) {
            List<CompanyReview> reviews = companyReviewRepository.listCompanyReviewByCompanyId(companiesDetailJipyoDto.getId());
            AggregateCompany agg = reviews.stream().collect(AggregateCompany::new, AggregateCompany::add, AggregateCompany::merge);
            EvaluationScore escore = new EvaluationScore(company);
            UserScore uscore = new UserScore(agg);

            EvaluationScore evaluationScore = EvaluationScore.builder()
                    .scoreTot(escore.getScoreTot())
                    .scoreCareEnv(escore.getScoreCareEnv())
                    .scoreOprManage(escore.getScoreOprManage())
                    .scoreCareCourse(escore.getScoreCareCourse())
                    .scoreTeach(escore.getScoreTeach())
                    .scoreHealth(escore.getScoreHealth())
                    .scoreSafty(escore.getScoreSafty())
                    .build();
            companiesDetailJipyoDto.setEvaluationScore(evaluationScore);
            companiesDetailJipyoDto.setScoreA(evaluationScore.getScoreTot());

            UserScore userScore = UserScore.builder()
                    .scoreTot(uscore.getScoreTot())
                    .scoreKeepWork(uscore.getScoreKeepWork())
                    .scoreEvent(uscore.getScoreEvent())
                    .scoreDcoWork(uscore.getScoreDcoWork())
                    .scoreReadyClass(uscore.getScoreReadyClass())
                    .scorePersonalPC(uscore.getScorePersonalPC())
                    .scoreSelfDev(uscore.getScoreSelfDev())
                    .scoreKizRest(uscore.getScoreKizRest())
                    .scoreLeadership(uscore.getScoreLeadership())
                    .scorePartnership(uscore.getScorePartnership())
                    .scoreLeadership(uscore.getScoreLeadership())
                    .build();
            companiesDetailJipyoDto.setUserScore(userScore);
            companiesDetailJipyoDto.setScoreT(userScore.getScoreTot());
            companiesDetailJipyoDto.setScoreJ((companiesDetailJipyoDto.getScoreA()+ companiesDetailJipyoDto.getScoreT())/2);

        }
        return companiesDetailJipyoDto;
    }

}
