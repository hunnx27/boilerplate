package com.onz.modules.review.application;

import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.infra.dto.DistinctAddressResponse;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.web.dto.reponse.CompanyReviewListResponseDto;
import com.onz.modules.company.web.dto.reponse.InterviewListResponseDto;
import com.onz.modules.company.web.dto.reponse.InterviewcountResponsedto;
import com.onz.modules.company.web.dto.reponse.YearAmtListResponseDto;
import com.onz.modules.company.web.dto.request.AvgReqestDto;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.onz.modules.company.infra.CompanyRepository;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.domain.dto.ReviewAll;
import com.onz.modules.review.infra.AmtReviewRepository;
import com.onz.modules.review.infra.CompanyReviewRepository;
import com.onz.modules.review.infra.InterviewReviewRepository;
import com.onz.modules.review.infra.ReviewRepository;
import com.onz.modules.review.web.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Double.max;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;
    private final CompanyReviewRepository companyReviewRepository;
    private final InterviewReviewRepository interviewReviewRepository;
    private final AmtReviewRepository amtReviewRepository;
    private final AddressRepository addressRepository;
    private int writCount = 0;
    private int patCount = 0;
    private int mockCount = 0;
    private int lowLevCount = 0;
    private int midLevCount = 0;
    private int highLevCount = 0;
    private String totalLevel;
    private String totalGoal;
    private int companyCount = 0;
    double totalLevelPer = 0;
    double totalGoalPer=0;
    private int goalCount=0;
    private int waitCount=0;
    private int noCount=0;


    public Page<Company> list(CompanySearchRequest searchRequest) {
        return companyRepository.list(searchRequest);
    }

    public List<YearAmtListResponseDto> companySearchAmt(@PathVariable Long id) {
        List<YearAmtListResponseDto> list = amtReviewRepository.findByCompanyId(id);
        List<YearAmtListResponseDto> array = list.stream().map(res -> {
        List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(res.zoneCode);
        if (addressList.size() > 0) {
            DistinctAddressResponse address = addressList.get(0);
            String mapsidogunguName = address.getSidoName() + " " + address.getSigunguName();
            res.setMapsidogunguName(mapsidogunguName);
        }
        return res;
    }).collect(Collectors.toList());
        return array;
    }

    public List<CompanyReviewListResponseDto> companySearchCompany(@PathVariable Long id) {
        List<CompanyReview> list = companyReviewRepository.findByCompanyId(id);
        List<CompanyReviewListResponseDto> array = list.stream().map(res -> {
            CompanyReviewListResponseDto bbb = new CompanyReviewListResponseDto(res);
            List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(res.getCompany().getZonecode());
            if (addressList.size() > 0) {
                DistinctAddressResponse address = addressList.get(0);
                String mapsidogunguName = address.getSidoName() + " " + address.getSigunguName();
                bbb.setMapsidogunguName(mapsidogunguName);
            }
            return bbb;
        }).collect(Collectors.toList());
        return array;
    }

    public List<InterviewListResponseDto> companySearchInterview(@PathVariable Long id) {
        List<InterviewReview> list = interviewReviewRepository.findByCompanyId(id);
        List<InterviewListResponseDto> array = list.stream().map(res -> {
            InterviewListResponseDto bbb = new InterviewListResponseDto(res);
            List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(res.getZonecode());
            if (addressList.size() > 0) {
                DistinctAddressResponse address = addressList.get(0);
                String mapsidogunguName = address.getSidoName() + " " + address.getSigunguName();
                bbb.setMapsidogunguName(mapsidogunguName);
            }
            return bbb;
        }).collect(Collectors.toList());
//            companySearchInterviewCount(writCount,patCount);
        return array;
    }

    public InterviewcountResponsedto companySearchInterviewCount(@PathVariable Long id) {
        writCount = 0;
        patCount = 0;
        mockCount = 0;
        companyCount = 0;
        lowLevCount = 0;
        midLevCount = 0;
        highLevCount = 0;
        goalCount=0;
        waitCount=0;
        noCount=0;

        List<InterviewReview> list = interviewReviewRepository.findByCompanyId(id);
        List<InterviewListResponseDto> array = list.stream().map(res -> {
            companyCount++;
            if (res.getItem_2().name().equals("Y")) {
                writCount += 1;
            }
            if (res.getItem_3().name().equals("Y")) {
                patCount += 1;
            }
            if(res.getItem_1()!=null){  //FIXME (NULL OR N)
                mockCount+=1;
            }

            int key = Integer.parseInt(res.getItem_5());
            switch (key) {
                case 1:
                    lowLevCount += 1;
                    break;
                case 2:
                    midLevCount += 1;
                    break;
                case 3:
                    highLevCount += 1;
                    break;
            }
            int key1 = Integer.parseInt(res.getItem_4());
            switch (key1){
                case 1:
                    goalCount +=1;
                    break;
                case 2:
                    waitCount +=1;
                    break;
                case 3:
                    noCount +=1;
                    break;
            }


            InterviewListResponseDto bbb = new InterviewListResponseDto(res);
            return bbb;
        }).collect(Collectors.toList());
//            companySearchInterviewCount(writCount,patCount);
        if(companyCount!=0){
            lowLevCount = (lowLevCount * 100) / companyCount;
            midLevCount = (midLevCount * 100) / companyCount;
            highLevCount = (highLevCount * 100) / companyCount;
            goalCount = (goalCount * 100) / companyCount;
            waitCount = (waitCount * 100) / companyCount;
            noCount = (noCount * 100) / companyCount;
        }else{
            lowLevCount = 0;
            midLevCount = 0;
            highLevCount = 0;
            goalCount = 0;
            waitCount = 0;
            noCount = 0;
        }
        totalLevelPer = max(max(lowLevCount, midLevCount), highLevCount);
        totalGoalPer = max(max(goalCount, waitCount), noCount);

        if (totalLevelPer == lowLevCount) {
            totalLevel = "lowLevel";
        } else if (totalLevelPer == midLevCount) {
            totalLevel = "midLevel";
        } else {
            totalLevel = "highLevel";
        }
        // 합격여부
        if (totalGoalPer == goalCount) {
            totalGoal = "Goal";
        } else if (totalGoalPer == waitCount) {
            totalGoal = "Wait";
        } else {
            totalGoal = "Failed";
        }


        return new InterviewcountResponsedto(writCount, patCount, mockCount, lowLevCount, midLevCount, highLevCount, totalLevel,goalCount,waitCount,noCount,totalGoal);
    }

    public List<ReviewResponseDto> findByAllReview(Pageable pageable) {
        List<ReviewAll> list = reviewRepository.findByAllReview(pageable);
//        List<ReviewAll>  >> List<ReviewResponseDto>
        List<ReviewResponseDto> array = list.stream().map(res -> {
            ReviewResponseDto aaa = new ReviewResponseDto(res);

            aaa.setImpCost(res.getImpCost());
            if (res.getEtcItems() != null) {
                String[] one = res.getEtcItems().split(",");
                String[] two = res.getEtcAmt().split(",");
                int total = 0;
                Map<String, String> map = new HashMap<>();
                for (int i = 0; i < one.length; i++) {
                    String key = one[i];
                    String value = two[i];


                    map.put(key, value);
                    total += Integer.parseInt(value);

                    switch (key) {
                        case "1":
                            aaa.setImpCost(value);
                            break;
                        case "2":
                            aaa.setWorkCost(value);
                            break;
                        case "3":
                            aaa.setAddCost(value);
                            break;
                        case "4":
                            aaa.setEtcCost(value);
                            break;
                    }
                    aaa.setTotalCost((long) total);
                }
            }
            List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(res.getZonecode());
            if (addressList.size() > 0) {
                DistinctAddressResponse address = addressList.get(0);
                String mapsidogunguName = address.getSidoName() + " " + address.getSigunguName();
                aaa.setMapsidogunguName(mapsidogunguName);
            }
            return aaa;
        }).collect(Collectors.toList());
        return array;
    }

    public void create(Company company) {
        companyRepository.save(company);
    }

    public void update(CompanyUpdateRequest updateRequest) {
        companyRepository.update(updateRequest);
    }

    public Company findOne(Long id) {
        return companyRepository.findById(id)
                .orElseThrow();
    }
}
