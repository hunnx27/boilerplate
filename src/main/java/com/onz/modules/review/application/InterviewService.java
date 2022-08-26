package com.onz.modules.review.application;

import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.infra.dto.DistinctAddressResponse;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.infra.CompanyRepository;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.InterviewReviewItem;
import com.onz.modules.review.infra.InterviewReviewRepository;
import com.onz.modules.review.infra.InterviewReviewItemRepository;
import com.onz.modules.review.web.dto.InterviewListResponseDto;
import com.onz.modules.review.web.dto.InterviewRequestDto;
import com.onz.modules.review.web.dto.YearAmtListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class InterviewService {
    private final InterviewReviewRepository interviewReviewRepository;
    private final CompanyRepository companyRepository;
    private final AccountService accountService;
    private final AddressRepository addressRepository;
    private final InterviewReviewItemRepository interviewReviewItemRepository;

    public void create(InterviewRequestDto interviewRequestDto, UserPrincipal me) {
        Long companyId = interviewRequestDto.getCompanyId();
        Account account = accountService.findOne(me.getId());
        Company company = companyRepository.findById(companyId).orElse(null);
//        List<InterviewResponseDto> interviews=InterviewReview;
//        for(InterviewResponseDto interview : interviews){
//            InterviewReviews interviewReviews = new InterviewReviews().getInterviewReview();
//            // ..save()
//        }

        InterviewReview interviewReview = new InterviewReview(interviewRequestDto, company,account);
        InterviewReview mom = interviewReviewRepository.save(interviewReview);

        List<InterviewRequestDto.Interview> interviews = interviewRequestDto.getInterviews();
        for(int i=0; i<interviews.size(); i++){
            //sava...
            InterviewRequestDto.Interview itv = interviews.get(i);
            InterviewReviewItem interviewReviewItem = new InterviewReviewItem(itv, mom);
            interviewReviewItemRepository.save(interviewReviewItem);
        }
    }
    public List<InterviewListResponseDto> interviewReviewList() {
        List<InterviewListResponseDto> list =  interviewReviewRepository.ListInterview(interviewReviewRepository.findAll());
        List<InterviewListResponseDto> array = list.stream().map(res -> {
//           String q_1 =(interviewReviewItemRepository.getById(res.getId()).getInterviewQ());
//            res.setQ_1(q_1);
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