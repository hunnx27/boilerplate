package com.onz.modules.counsel.application;

import com.onz.common.util.FileUtil;
import com.onz.common.util.dto.AttachDto;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.infra.CounselRepository;
import com.onz.modules.counsel.web.dto.request.CounselCreateRequest;
import com.onz.modules.counsel.web.dto.response.CounselResponse;
import com.onz.modules.organization.domain.Organization;
import com.onz.modules.organization.infra.OrganizationRepository;
import com.onz.modules.organization.web.dto.request.OrganizationSearchRequest;
import com.onz.modules.organization.web.dto.request.OrganizationUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    public void create(CounselCreateRequest counselCreateRequest, UserPrincipal me) {
        Account one = accountService.findOne(me.getId());
        Counsel counsel = new Counsel(counselCreateRequest, one);
        Counsel saved = counselRepository.save(counsel);

        // Image File Upload
        if(counselCreateRequest.getFiles()!=null && counselCreateRequest.getFiles().size()>0){
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(counselCreateRequest.getFiles(), saved.getId());
                saved.setImages(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<CounselResponse> list(Pageable pageable, UserPrincipal me){
        Account account = accountService.findOne(me.getId());
        List<Counsel> list = counselRepository.findAll(pageable).get().collect(Collectors.toList());
        List<CounselResponse> result = list.stream().map(counsel->new CounselResponse(counsel, account)).collect(Collectors.toList());
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
