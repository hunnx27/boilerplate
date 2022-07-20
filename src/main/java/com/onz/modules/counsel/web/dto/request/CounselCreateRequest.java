package com.onz.modules.counsel.web.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.common.enums.*;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.embed.Images;
import com.onz.modules.counsel.domain.enums.CounselState;
import com.onz.modules.counsel.domain.enums.JobGubn;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.counsel.domain.enums.QnaItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CounselCreateRequest {

    @Convert(converter = GubnConverter.class)
    private Gubn gubn = Gubn.PARENT;
    @Enumerated
    private JobGubn jobGubn;
    @Enumerated
    private QnaGubn qnaGubn;
    @Enumerated
    private CounselState counselState;
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private ZonedDateTime counselStateAt;
    @Enumerated
    private YN openYn;
    @Enumerated
    private YN shortOpenYn;
    @Convert(converter = InterestOrgConverter.class)
    private InterestOrg interestOrg; // 관심 기관
    @Enumerated
    private QnaItem qnaItem;
    private long answerCnt;
    private String txt;
    @Embedded
    private Images images;

}
