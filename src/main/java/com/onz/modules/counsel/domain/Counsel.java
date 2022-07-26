package com.onz.modules.counsel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.common.domain.BaseEntity;
import com.onz.common.enums.Gubn;
import com.onz.common.enums.GubnConverter;
import com.onz.common.enums.YN;
import com.onz.common.enums.InterestOrg;
import com.onz.common.enums.InterestOrgConverter;
import com.onz.common.util.dto.AttachDto;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.embed.Images;
import com.onz.modules.counsel.domain.enums.CounselState;
import com.onz.modules.counsel.domain.enums.JobGubn;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.counsel.domain.enums.QnaItem;
import com.onz.modules.counsel.web.dto.request.CounselQCreateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Counsel extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Convert(converter = GubnConverter.class)
    private Gubn gubn = Gubn.PARENT;
    @Enumerated(EnumType.STRING)
    private JobGubn jobGubn;
    @Enumerated(EnumType.STRING)
    private QnaGubn qnaGubn;
    @Enumerated(EnumType.STRING)
    private CounselState counselState;
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private ZonedDateTime counselStateAt;
    @Enumerated(EnumType.STRING)
    private YN openYn;
    @Enumerated(EnumType.STRING)
    private YN shortOpenYn;
    @Convert(converter = InterestOrgConverter.class)
    private InterestOrg interestOrg; // 관심 기관
    @Enumerated(EnumType.STRING)
    private QnaItem qnaItem;

    private long reportCnt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @Size(max=1000)
    private String txt;
    @Embedded
    private Images images;

    private String inputTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName="id", name = "parent_counsel_id")
    private Counsel parentCounsel;

    public Counsel() {
    }

    @Builder
    public Counsel(CounselQCreateRequest req, Account account) {
        this.account = account;
        this.gubn = account.getGubn();
        this.jobGubn = JobGubn.J;
        this.qnaGubn = QnaGubn.Q;
        this.counselState = CounselState.R;
        this.openYn = YN.Y;
        this.shortOpenYn = req.getShortOpenYn();
        this.interestOrg = req.getInterestOrgName();
        this.qnaItem = req.getQnaItem();
        this.txt = req.getTxt();
        String inputTag = this.qnaItem.getTag();
        if(req.getAddedTagData()!=null && !"".equals(req.getAddedTagData())){
            String[] tagArr = req.getAddedTagData().split(",");
            for(int i=0; i<tagArr.length; i++){
                inputTag += " #" + tagArr[i];
            }

        }
        this.inputTag = inputTag;
    }

    public void setImages(List<AttachDto> filelist){
        Images embedImages = new Images();
        if(filelist!=null && filelist.size()>0){
            for(int i=0; i<filelist.size(); i++){
                AttachDto atttach = filelist.get(i);
                switch(i){
                    case 0: embedImages.setImage1(atttach.getFilePath());break;
                    case 1: embedImages.setImage2(atttach.getFilePath());break;
                    case 2: embedImages.setImage3(atttach.getFilePath());break;
                    case 3: embedImages.setImage4(atttach.getFilePath());break;
                    case 4: embedImages.setImage5(atttach.getFilePath());break;
                }
            }
            this.images = embedImages;
        }
    }
}
