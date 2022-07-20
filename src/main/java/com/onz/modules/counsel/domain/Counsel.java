package com.onz.modules.counsel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.common.domain.BaseEntity;
import com.onz.common.enums.Gubn;
import com.onz.common.enums.GubnConverter;
import com.onz.common.enums.YN;
import com.onz.common.enums.InterestOrg;
import com.onz.common.enums.InterestOrgConverter;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.embed.Images;
import com.onz.modules.counsel.domain.enums.CounselState;
import com.onz.modules.counsel.domain.enums.JobGubn;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.counsel.domain.enums.QnaItem;
import com.onz.modules.counsel.web.dto.request.CounselCreateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
public class Counsel extends BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Size(max=50)
    private Long idxNo;
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
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Size(max=1000)
    private String txt;
    @Embedded
    private Images images;

    public Counsel() {
    }

    @Builder
    public Counsel(CounselCreateRequest req) {
        this.gubn = req.getGubn();
        this.jobGubn = req.getJobGubn();
        this.qnaGubn = req.getQnaGubn();
        this.counselState = req.getCounselState();
        this.counselStateAt = req.getCounselStateAt();
        this.openYn = req.getOpenYn();
        this.shortOpenYn = req.getShortOpenYn();
        this.interestOrg = req.getInterestOrg();
        this.qnaItem = req.getQnaItem();
        this.answerCnt = req.getAnswerCnt();
        this.txt = req.getTxt();
        this.images = req.getImages();
    }
}
