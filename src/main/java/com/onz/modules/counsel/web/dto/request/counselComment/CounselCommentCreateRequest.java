package com.onz.modules.counsel.web.dto.request.counselComment;

import com.onz.modules.counsel.domain.Counsel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CounselCommentCreateRequest {
    @NotNull(message = " 반드시 입력해야 합니다")
    private String txt;
    @NotNull(message = " 반드시 입력해야 합니다")
    private Long parentCounselId;
    @NotNull(message = " 반드시 입력해야 합니다")
    private Counsel parentCounsel;

    // 자동처리
//    @Convert(converter = GubnConverter.class)
//    private Gubn gubn;
//    private JobGubn jobGubn;
//    private QnaGubn qnaGubn=QnaGubn.Q;





}
