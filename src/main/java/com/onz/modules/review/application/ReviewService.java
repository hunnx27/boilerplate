package com.onz.modules.review.application;

import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.infra.dto.DistinctAddressResponse;
import com.onz.modules.company.domain.Company;
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
import com.onz.modules.review.web.dto.AvgReqestDto;
import com.onz.modules.review.web.dto.CompanyReviewListResponseDto;
import com.onz.modules.review.web.dto.InterviewListResponseDto;
import com.onz.modules.review.web.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Page<Company> list(CompanySearchRequest searchRequest) {
        return companyRepository.list(searchRequest);
    }

    public List<YearAmtReview> companySearchAmt(AvgReqestDto avgReqestDto) {
        return amtReviewRepository.findByCompanyId(avgReqestDto.getCompanyId());
    }

    public List<CompanyReviewListResponseDto> companySearchCompany(AvgReqestDto avgReqestDto) {
        List<CompanyReview> list = companyReviewRepository.findByCompanyId(avgReqestDto.getCompanyId());
        List<CompanyReviewListResponseDto> array = list.stream().map(res -> {
            CompanyReviewListResponseDto bbb = new CompanyReviewListResponseDto(res);
            return bbb;
        }).collect(Collectors.toList());
        return array;
    }
    public List<InterviewListResponseDto> companySearchInterview (AvgReqestDto avgReqestDto){
        List<InterviewReview> list = interviewReviewRepository.findByCompanyId(avgReqestDto.getCompanyId());
        List<InterviewListResponseDto> array = list.stream().map(res -> {
            InterviewListResponseDto bbb = new InterviewListResponseDto(res);
            return bbb;
        }).collect(Collectors.toList());
        return array;
    }

    public List<ReviewResponseDto> findByAllReview(Pageable pageable){
        List<ReviewAll> list = reviewRepository.findByAllReview(pageable);
//        List<ReviewAll>  >> List<ReviewResponseDto>
        List<ReviewResponseDto> array = list.stream().map(res->{
            ReviewResponseDto aaa = new ReviewResponseDto(res);

            aaa.setImpCost(res.getImpCost());
            if(res.getEtcItems()!=null){
                String[] one = res.getEtcItems().split(",");
                String[] two = res.getEtcAmt().split(",");
                int total =0;
                Map<String,String> map = new HashMap<>();
                for(int i=0; i<one.length; i++){
                    String key = one[i];
                    String value = two[i];


                    map.put(key, value);
                    total += Integer.parseInt(value);

                    switch (key){
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
                List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(res.getZonecode());
                if (addressList.size() > 0) {
                    DistinctAddressResponse address = addressList.get(0);
                    String mapsidogunguName = address.getSidoName() + " " + address.getSigunguName();
                    aaa.setMapsidogunguName(mapsidogunguName);
                }
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
