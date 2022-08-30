package com.onz.modules.review.application;

import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.infra.dto.DistinctAddressResponse;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.infra.CompanyRepository;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.infra.AmtReviewRepository;
import com.onz.modules.review.web.dto.AmtRequestDto;
import com.onz.modules.review.web.dto.YearAmtListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AmtReviewService {

    private final AmtReviewRepository amtReviewRepository;
    private final CompanyRepository companyRepository;
    private final AccountService accountService;
    private final AddressRepository addressRepository;

    public void create(AmtRequestDto amtRequestDto, UserPrincipal me) {
        Long companyId = amtRequestDto.getCompanyId();
        Account account = accountService.findOne(me.getId());
        Company company = companyRepository.findById(companyId).orElse(null);
        YearAmtReview yearAmtReview = new YearAmtReview(amtRequestDto, company, account);
        amtReviewRepository.save(yearAmtReview);
    }

    public List<YearAmtListResponseDto> amtReviewList(Pageable pageable) {
        List<YearAmtListResponseDto> list = amtReviewRepository.ListAmt(amtReviewRepository.findAll(pageable).toList());
        List<YearAmtListResponseDto> array = list.stream().map(res -> {
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
                        System.out.println(key+"+"+value);
                        res.setImpCost(value);
                        break;
                    case "2":
                        System.out.println(key+"+"+value);
                        res.setWorkCost(value);
                        break;
                    case "3":
                        System.out.println(key+"+"+value);
                        res.setAddCost(value);
                        break;
                    case "4":
                        System.out.println(key+"+"+value);
                        res.setEtcCost(value);
                        break;
                }
                res.setTotalCost((long) total);
            }

            List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(res.getZonecode());
            if (addressList.size() > 0) {
                DistinctAddressResponse address = addressList.get(0);
                String mapsidogunguName = address.getSidoName() + " " + address.getSigunguName();
                res.setMapsidogunguName(mapsidogunguName);
            }
            return res;
        }).collect(Collectors.toList());
        return array;
    }
}