package com.onz.modules.counsel.web.dto.request.counsel;

import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.enums.CounselState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class
CounselAAdoptRequest {
    @NotNull(message = "내용을 입력해주세요.")
    @Size(min = 6,message = "를 3글자 이상 입력해주세요")
    private String commentTxt;
    @Min(value = 1,message = "숫자만 입력됩니다.")
    private Long parentCounselId;
    private Counsel parentCounsel;
    private Long answerAccountId;


    // 자동처리
//    @Convert(converter = GubnConverter.class)
//    private Gubn gubn;
//    private JobGubn jobGubn;
//    private QnaGubn qnaGubn=QnaGubn.Q;





}
