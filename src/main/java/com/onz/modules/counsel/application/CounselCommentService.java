package com.onz.modules.counsel.application;

import com.onz.common.enums.YN;
import com.onz.common.util.FileUtil;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.counsel.domain.CounselComment;
import com.onz.modules.counsel.infra.counselComment.CounselCommentRepository;
import com.onz.modules.counsel.web.dto.request.counselComment.CounselCommentCreateRequest;
import com.onz.modules.counsel.web.dto.request.counselComment.CounselCommentUpdateRequest;
import com.onz.modules.counsel.web.dto.response.counselComment.CounselCommentDetailResponse;
import com.onz.modules.counsel.web.dto.response.counselComment.CounselCommentListResponse;
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
public class CounselCommentService {

    private final AccountService accountService;
    private final CounselCommentRepository counselCommentRepository;
    private final FileUtil fileUtil;

    public void create(CounselCommentCreateRequest counselCommentCreateRequest, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        CounselComment counselComment = new CounselComment(counselCommentCreateRequest, account);
        CounselComment saved = counselCommentRepository.save(counselComment);
    }

    public List<CounselCommentListResponse> commentList(Long answerId, Pageable pageable, UserPrincipal me){
        Account account = accountService.findOne(me.getId());
        List<CounselComment> list = counselCommentRepository.findAll(pageable).get().collect(Collectors.toList());
        List<CounselCommentListResponse> result = list.stream().map(counselComment->new CounselCommentListResponse(counselComment, account)).collect(Collectors.toList());
        return result;
    }

    public CounselCommentDetailResponse detail(Long id, UserPrincipal me){
        CounselComment counselComment = counselCommentRepository.findById(id).orElse(null);
        CounselCommentDetailResponse result = null;
        if(counselComment != null){
            Account account = accountService.findOne(me.getId());
            result = new CounselCommentDetailResponse(counselComment, account);
        }
        return result;
    }

    public CounselComment updateCounselComment(Long id, CounselCommentUpdateRequest counselCommentUpdateRequest, UserPrincipal me){
        Account account = accountService.findOne(me.getId());
        CounselComment counselComment = counselCommentRepository.findById(id).get();
        counselComment.updateCounselComment(counselCommentUpdateRequest, account);
        CounselComment saved = counselCommentRepository.save(counselComment);
        return saved;
    }

    public CounselComment deleteCounselCommentSoft(Long id){
        CounselComment counselComment = counselCommentRepository.findById(id).orElseThrow();
        counselComment.setIsDelete(YN.Y);
        counselCommentRepository.save(counselComment);
        return counselComment;
    }

}
