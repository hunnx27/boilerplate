package com.onz.modules.counsel.web.dto.request.counsel;

import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.enums.CounselState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CounselAAdoptRequest {

    Long answerId;
    private String commentTxt;
    private Long parentCounselId;
    private Counsel parentCounsel;
    private Long answerAccountId;


    // 자동처리
//    @Convert(converter = GubnConverter.class)
//    private Gubn gubn;
//    private JobGubn jobGubn;
//    private QnaGubn qnaGubn=QnaGubn.Q;





}
