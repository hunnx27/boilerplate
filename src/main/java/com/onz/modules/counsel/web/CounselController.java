package com.onz.modules.counsel.web;

import com.onz.common.web.BaseApiController;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.counsel.application.CounselService;
import com.onz.modules.counsel.web.dto.request.CounselQCreateRequest;
import com.onz.modules.counsel.web.dto.response.CounselDetailResponse;
import com.onz.modules.counsel.web.dto.response.CounselListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class CounselController extends BaseApiController {

    private final CounselService counselService;
    private final ModelMapper modelMapper;

//    @GetMapping("/counsel")
//    public Page<Organization> list(OrganizationSearchRequest organizationSearchRequest) {
//        return organizationService.list(organizationSearchRequest);
//    }

    @PostMapping("/counsel")
    public void create(@AuthenticationPrincipal UserPrincipal me, CounselQCreateRequest counselQCreateRequest) {
        counselService.create(counselQCreateRequest, me);
    }

    @GetMapping("/counsel")
    public List<CounselListResponse> list(@AuthenticationPrincipal UserPrincipal me, Pageable pageable){
        List<CounselListResponse> result = counselService.list(pageable, me);
        return result;
    }
    
    @GetMapping("/counsel/{id}")
    public CounselDetailResponse info(@AuthenticationPrincipal UserPrincipal me, @PathVariable Long id){
        CounselDetailResponse result = counselService.detail(id, me);
        return result;
    }

    @GetMapping("/counsel/answer/{id}")
    public List<CounselListResponse> answerList(@AuthenticationPrincipal UserPrincipal me, @PathVariable Long id, Pageable pageable){
        List<CounselListResponse> result = counselService.answerList(id, pageable, me);
        return result;
    }


//    @PatchMapping("/counsel/{id}")
//    public void update(@PathVariable Long id,
//        @RequestBody OrganizationUpdateRequest updateRequest) {
//        updateRequest.setId(id);
//        organizationService.update(updateRequest);
//    }

//    @GetMapping("/counsel/{id}")
//    public Organization findOne(@PathVariable Long id) {
//        return organizationService.findOne(id);
//    }

}
