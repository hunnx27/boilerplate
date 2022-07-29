package com.onz.modules.counsel.application;

import com.onz.common.enums.YN;
import com.onz.common.util.FileUtil;
import com.onz.common.util.dto.AttachDto;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.infra.counsel.CounselRepository;
import com.onz.modules.counsel.web.dto.request.counsel.*;
import com.onz.modules.counsel.web.dto.response.CounselAnswerListResponse;
import com.onz.modules.counsel.web.dto.response.counsel.CounselDetailResponse;
import com.onz.modules.counsel.web.dto.response.counsel.CounselListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CounselService {

    private final AccountService accountService;
    private final CounselRepository counselRepository;
    private final FileUtil fileUtil;

//    public Page<Organization> list(OrganizationSearchRequest organizationSearchRequest) {
//        return CounselService.this.counselRepository.list(organizationSearchRequest);
//    }

    public void create(Counsel counsel) {
        CounselService.this.counselRepository.save(counsel);
    }

    public void create(CounselQCreateRequest counselQCreateRequest, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        Counsel counsel = new Counsel(counselQCreateRequest, account);
        Counsel saved = counselRepository.save(counsel);

        // Image File Upload
        if(counselQCreateRequest.getFiles()!=null && counselQCreateRequest.getFiles().size()>0){
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(counselQCreateRequest.getFiles(), saved.getId());
                saved.setImages(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<CounselListResponse> list(Pageable pageable, UserPrincipal me){
        Account account = accountService.findOne(me.getId());
        List<Counsel> list = counselRepository.findCounselList(pageable);
        List<CounselListResponse> result = list.stream().map(counsel->new CounselListResponse(counsel, account)).collect(Collectors.toList());
        return result;
    }

    public CounselDetailResponse detail(Long id, UserPrincipal me){
        Counsel counsel = counselRepository.findById(id).orElse(null);
        CounselDetailResponse result = null;
        if(counsel != null){
            Account account = accountService.findOne(me.getId());
            result = new CounselDetailResponse(counsel, account);
        }
        return result;
    }

    public CounselDetailResponse updateCounsel(Long id, CounselQUpdateRequest counselQUpdateRequest, UserPrincipal me){
        Account account = accountService.findOne(me.getId());
        Counsel counsel = counselRepository.findById(id).get();
        counsel.updateCounsel(counselQUpdateRequest, account);
        Counsel saved = counselRepository.save(counsel);

        //TODO 검증필요
        //TODO 검증필요
        //TODO 검증필요
        // Image File Upload
        if(counselQUpdateRequest.getFiles()!=null && counselQUpdateRequest.getFiles().size()>0){
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(counselQUpdateRequest.getFiles(), saved.getId());
                saved.setImages(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new CounselDetailResponse(saved, account);
    }

    public Counsel deleteCounselSoft(Long id){
        Counsel counsel = counselRepository.findById(id).orElseThrow();
        counsel.setIsDelete(YN.Y);
        counselRepository.save(counsel);
        return counsel;
    }

    public List<CounselAnswerListResponse> answerList(Long counselId, Pageable pageable, UserPrincipal me){
        Account account = accountService.findOne(me.getId());
        //List<Counsel> list = counselRepository.findAll(pageable).get().collect(Collectors.toList());
        List<Counsel> list = counselRepository.findAnswerList(counselId, pageable);
        List<CounselAnswerListResponse> result = list.stream().map(counsel->new CounselAnswerListResponse(counsel, account)).collect(Collectors.toList());
        return result;
    }

    public void createAnswer(CounselACreateRequest counselACreateRequest, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        Long parentCounselId = counselACreateRequest.getParentCounselId();
        if(parentCounselId!=-1){
            Counsel parentCounsel = counselRepository.findById(parentCounselId).orElseGet(null);
            long cnt = counselRepository.countByParentCounselId(parentCounselId);
            parentCounsel.setReportCnt(cnt++);
            counselACreateRequest.setParentCounsel(parentCounsel);
        }
        Counsel counsel = new Counsel(counselACreateRequest, account);
        Counsel saved = counselRepository.save(counsel);

        // Image File Upload
        if(counselACreateRequest.getFiles()!=null && counselACreateRequest.getFiles().size()>0){
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(counselACreateRequest.getFiles(), saved.getId());
                saved.setImages(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Counsel updateAnswer(Long id, CounselAUpdateRequest counselAUpdateRequest, UserPrincipal me){
        Account account = accountService.findOne(me.getId());
        Counsel counsel = counselRepository.findById(id).get();
        counsel.updateAnswerCounsel(counselAUpdateRequest, account);
        Counsel saved = counselRepository.save(counsel);

        //TODO 검증필요
        //TODO 검증필요
        //TODO 검증필요
        // Image File Upload
        if(counselAUpdateRequest.getFiles()!=null && counselAUpdateRequest.getFiles().size()>0){
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(counselAUpdateRequest.getFiles(), saved.getId());
                saved.setImages(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return saved;
    }

    public Counsel deleteAnswerSoft(Long id){
        Counsel counsel = counselRepository.findById(id).orElseThrow();
        counsel.setIsDelete(YN.Y);
        counselRepository.save(counsel);
        return counsel;
    }

    public Counsel updateAnswerAdopt(Long id, CounselAAdoptRequest counselAAdoptRequest, UserPrincipal me){
//        Account account = accountService.findOne(me.getId());
        Counsel counsel = counselRepository.findById(id).get();
        Counsel parent = counselRepository.findById(counselAAdoptRequest.getParentCounselId()).orElseGet(null);
        parent.updateCounselAdopted();
        counselAAdoptRequest.setParentCounsel(parent);
        counsel.updateAnswerAdopt(counselAAdoptRequest);
        Counsel saved = counselRepository.save(counsel);
        return saved;
    }

//    public void update(OrganizationUpdateRequest updateRequest) {
//        CounselService.this.counselRepository.update(updateRequest);
//    }
//
//    public Organization findOne(Long id) {
//        return CounselService.this.counselRepository.findById(id)
//            .orElseThrow();
//    }
}
