package com.onz.modules.counsel.web;

import com.onz.common.web.BaseApiController;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.counsel.application.CounselService;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.web.dto.request.counsel.CounselACreateRequest;
import com.onz.modules.counsel.web.dto.request.counsel.CounselAUpdateRequest;
import com.onz.modules.counsel.web.dto.request.counsel.CounselQCreateRequest;
import com.onz.modules.counsel.web.dto.request.counsel.CounselQUpdateRequest;
import com.onz.modules.counsel.web.dto.response.counsel.CounselDetailResponse;
import com.onz.modules.counsel.web.dto.response.counsel.CounselListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class CounselController extends BaseApiController {

    private final CounselService counselService;
    private final ModelMapper modelMapper;

    /**
     *
     *      QUESTION
     *
     */

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

    @PutMapping("/counsel/{id}")
    public ResponseEntity<?> updateCounsel(@AuthenticationPrincipal UserPrincipal up, CounselQUpdateRequest counselQUpdateRequest, @PathVariable Long id) {
        CounselDetailResponse counselDetail = counselService.updateCounsel(id, counselQUpdateRequest, up);
        return ResponseEntity.ok(counselDetail);
    }

    @DeleteMapping("/counsel/{id}")
    public ResponseEntity<?> deleteCounsel(@PathVariable Long id) {
        Counsel counsel = counselService.deleteCounselSoft(id);
        return ResponseEntity.ok(new CounselDetailResponse(counsel));
    }

    /**
     *
     *      ANSWER
     *
     */

    @GetMapping("/counsel/answer/{id}")
    public List<CounselListResponse> answerList(@AuthenticationPrincipal UserPrincipal me, @PathVariable Long id, Pageable pageable){
        List<CounselListResponse> result = counselService.answerList(id, pageable, me);
        return result;
    }

    @PostMapping("/counsel/answer")
    public void createAnswer(@AuthenticationPrincipal UserPrincipal me, CounselACreateRequest counselACreateRequest) {
        counselService.createAnswer(counselACreateRequest, me);
    }

    @PutMapping("/counsel/answer/{id}")
    public ResponseEntity<?> updateAnswer(@AuthenticationPrincipal UserPrincipal up, CounselAUpdateRequest counselAUpdateRequest, @PathVariable Long id) {
        Counsel counsel = counselService.updateAnswer(id, counselAUpdateRequest, up);
        return ResponseEntity.ok(counsel);
    }

    @DeleteMapping("/counsel/answer/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long id) {
        Counsel counsel = counselService.deleteAnswerSoft(id);
        return ResponseEntity.ok(counsel);
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
