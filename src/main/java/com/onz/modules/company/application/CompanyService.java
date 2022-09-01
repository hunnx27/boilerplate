package com.onz.modules.company.application;

import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.infra.dto.DistinctAddressResponse;
import com.onz.modules.company.application.util.AggregateCompany;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.web.dto.reponse.CompanyDetailResponse;
import com.onz.modules.company.web.dto.reponse.CompanySearchResponse;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.onz.modules.company.infra.CompanyRepository;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.infra.CompanyReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;
    private final CompanyReviewRepository companyReviewRepository;

    public Page<Company> list(CompanySearchRequest searchRequest) {
        return companyRepository.list(searchRequest);
    }

    public void create(Company company) {
        companyRepository.save(company);
    }

    public void update(CompanyUpdateRequest updateRequest) {
        companyRepository.update(updateRequest);
    }

    public CompanyDetailResponse findOne(Long id) {
        Company company = companyRepository.findById(id).orElseThrow();
        CompanyDetailResponse rs = new CompanyDetailResponse(company);
        List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(company.getZonecode());
        if (addressList.size() > 0) {
            DistinctAddressResponse address = addressList.get(0);
            String mapsidogunguName = address.getSidoName() + " " + address.getSigunguName();
            rs.setMapsidogunguName(mapsidogunguName);
        }
        return rs;
    }

    public Map<String,Object> findOneJipyo(Long id) {
        Company company = companyRepository.findById(id).orElseThrow();
        List<CompanyReview> reviews = companyReviewRepository.listCompanyReviewByCompanyId(company.getId());

//        reviews.stream().map(review -> {
//            // TODO 계산식 구현 필요
//            // 유아교사 기관평가
//            int 근속; // sum A1 / count A1	20
//            int 행사; // sum B2 / count A1	50
//            int 서류; // sum B3 / count A1	60
//            int 수업준비;   // sum B4 / count A1	20
//            int 개선PC;   // sum C5 / count A1	60
//            int 자기개발;   // sum C6 / count A1	20
//            int 육아휴직;   // sum C7 / count A1	20
//            int 동료관계;   // sum D8 / count A1	50
//            int 리더쉽;    // sum D9 / count A1	40
//            return review;
//        }).collect(Collectors.toList());
        Map<String,Object> rsTot = new HashMap<>();
        rsTot.put("기관명", company.getOfficeName());
        AggregateCompany agg = reviews.stream().collect(AggregateCompany::new, AggregateCompany::add, AggregateCompany::merge);
        Map<String,Object> rs1 = new LinkedHashMap<>();
        rs1.put("SUM_STR", agg.toStringSum());
        rs1.put("총평균", agg.getAvgTotal());
        rs1.put("근속", agg.getAvgLikeCode());
        rs1.put("행사", agg.getAvgItemB1());
        rs1.put("서류", agg.getAvgItemB2());
        rs1.put("수업준비", agg.getAvgItemB3());
        rs1.put("개인PC", agg.getAvgItemC1());
        rs1.put("자기개발", agg.getAvgItemC2());
        rs1.put("육아휴직", agg.getAvgItemC3());
        rs1.put("동료관계", agg.getAvgItemD1());
        rs1.put("리더쉽", agg.getAvgItemD2());
        rsTot.put("유아교사 기관평가", rs1);

        //평가인증
        double 평가인증 = Math.round(Double.parseDouble(company.getEvalItems().split(",")[0]) * 10) / 10;
        double 보육환경 = Math.round(Double.parseDouble(company.getEvalItems().split(",")[1]) * 10) / 10;
        double 운영관리 = Math.round(Double.parseDouble(company.getEvalItems().split(",")[2]) * 10) / 10;
        double 보육과정 = Math.round(Double.parseDouble(company.getEvalItems().split(",")[3]) * 10) / 10;
        double 상호작용과교수법 = Math.round(Double.parseDouble(company.getEvalItems().split(",")[4]) * 10) / 10;
        double 건강과영양 = Math.round(Double.parseDouble(company.getEvalItems().split(",")[5]) * 10) / 10;
        double 안전 = Math.round(Double.parseDouble(company.getEvalItems().split(",")[6]) * 10) / 10;
        Map<String,Object> rs2 = new LinkedHashMap<>();
        rs2.put("평가인증", 평가인증);
        rs2.put("보육환경", 보육환경);
        rs2.put("운영관리", 운영관리);
        rs2.put("보육과정", 보육과정);
        rs2.put("상호작용과교수법", 상호작용과교수법);
        rs2.put("건강과영양", 건강과영양);
        rs2.put("안전", 안전);
        rsTot.put("평가인증", rs2);
//        CompanyDetailResponse rs = new CompanyDetailResponse(company);
//        List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(company.getZonecode());
//        if (addressList.size() > 0) {
//            DistinctAddressResponse address = addressList.get(0);
//            String mapsidogunguName = address.getSidoName() + " " + address.getSigunguName();
//            rs.setMapsidogunguName(mapsidogunguName);
//        }
        return rsTot;
    }


    public List<CompanySearchResponse> search(CompanySearchRequest companySearchRequest) {
        List<CompanySearchResponse> list = companyRepository.search(companySearchRequest);
        List<CompanySearchResponse> array = list.stream().map(res -> {
//            Integer sidocode = Integer.valueOf(res.getZonecode().substring(0, 2));
//            String guguncode = res.getZonecode().substring(3, 5);
//            String sidoName = addressRepository.findDistinctBySidoCode(sidocode).get(Integer.parseInt(guguncode)).getSidoName();
//            String gubuName= addressRepository.findDistinctBySidoCode(sidocode).get(Integer.parseInt(guguncode)).getSigunguName();

            List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(res.getZonecode());
            if(addressList.size()>0) {
                DistinctAddressResponse address = addressList.get(0);
                String sidoName = address.getSidoName();
                String gubuName = address.getSigunguName();
                String mapsidogunguName = address.getSidoName()+" "+address.getSigunguName();
                System.out.println(address.getSidoName());
                System.out.println(address.getSigunguName());
                res.setGubuName(gubuName);
                res.setSidoName(sidoName);
                res.setMapsidogunguName(mapsidogunguName);
            }
//            return new CompanySearchResponse();
            return res;
        }).collect(Collectors.toList());
        return array;
    }
}
