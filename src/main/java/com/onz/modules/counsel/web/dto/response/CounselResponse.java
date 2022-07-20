package com.onz.modules.counsel.web.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.common.enums.*;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.embed.Images;
import com.onz.modules.counsel.domain.enums.CounselState;
import com.onz.modules.counsel.domain.enums.JobGubn;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.counsel.domain.enums.QnaItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Enumerated;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CounselResponse {

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

    public CounselResponse(Counsel counsel) {
        this.gubn = counsel.getGubn();
        this.jobGubn = counsel.getJobGubn();
        this.qnaGubn = counsel.getQnaGubn();
        this.counselState = counsel.getCounselState();
        this.counselStateAt = counsel.getCounselStateAt();
        this.openYn = counsel.getOpenYn();
        this.shortOpenYn = counsel.getShortOpenYn();
        this.interestOrg = counsel.getInterestOrg();
        this.qnaItem = counsel.getQnaItem();
        this.answerCnt = counsel.getAnswerCnt();
        this.txt = counsel.getTxt();
        this.images = counsel.getImages();
    }
}
