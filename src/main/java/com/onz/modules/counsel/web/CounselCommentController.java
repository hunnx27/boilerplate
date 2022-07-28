package com.onz.modules.counsel.web;

import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.counsel.domain.CounselComment;
import com.onz.modules.counsel.web.dto.request.counselComment.CounselCommentCreateRequest;
import com.onz.modules.counsel.web.dto.request.counselComment.CounselCommentUpdateRequest;
import com.onz.modules.counsel.web.dto.response.counselComment.CounselCommentListResponse;
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
public class CounselCommentController {
    private final com.onz.modules.counsel.application.CounselCommentService counselCommentService;
    private final ModelMapper modelMapper;
    /**
     *
     *      ANSWER COMMENT
     *
     */
    @GetMapping("/counsel/comment/answer/{id}")
    public List<CounselCommentListResponse> commentList(@AuthenticationPrincipal UserPrincipal me,
                                                        @PathVariable Long answerId,
                                                        Pageable pageable){
        List<CounselCommentListResponse> result = counselCommentService.commentList(answerId, pageable, me);
        return result;
    }

    @PostMapping("/counsel/comment")
    public void createComment(@AuthenticationPrincipal UserPrincipal me, CounselCommentCreateRequest counselCommentCreateRequest) {
        counselCommentService.create(counselCommentCreateRequest, me);
    }

    @PutMapping("/counsel/comment/{id}")
    public ResponseEntity<?> updateComment(@AuthenticationPrincipal UserPrincipal up,
                                           CounselCommentUpdateRequest counselCommentUpdateRequest,
                                           @PathVariable Long id) {
        CounselComment counselComment = counselCommentService.updateCounselComment(id, counselCommentUpdateRequest, up);
        return ResponseEntity.ok(counselComment);

    }

    @DeleteMapping("/counsel/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        CounselComment counselComment = counselCommentService.deleteCounselCommentSoft(id);
        return ResponseEntity.ok(counselComment);
    }
}
