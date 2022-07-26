package com.onz.modules.counsel.application;

import com.onz.common.util.FileUtil;
import com.onz.common.util.dto.AttachDto;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.infra.CounselRepository;
import com.onz.modules.counsel.web.dto.request.CounselQCreateRequest;
import com.onz.modules.counsel.web.dto.response.CounselDetailResponse;
import com.onz.modules.counsel.web.dto.response.CounselListResponse;
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
        Account one = accountService.findOne(me.getId());
        Counsel counsel = new Counsel(counselQCreateRequest, one);
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
        List<Counsel> list = counselRepository.findAll(pageable).get().collect(Collectors.toList());
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

//    public void update(OrganizationUpdateRequest updateRequest) {
//        CounselService.this.counselRepository.update(updateRequest);
//    }
//
//    public Organization findOne(Long id) {
//        return CounselService.this.counselRepository.findById(id)
//            .orElseThrow();
//    }
}
