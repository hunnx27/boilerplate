package com.onz.modules.counsel.web.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onz.common.util.TimeUtil;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.Counsel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CounselAnswerListResponse {

    private Long id;
    private String gubnName;
    private String txt;
    private Long accountId;
    @JsonProperty(value="isMine")
    private boolean isMine;
    private String time;
    private String commentTxt;
    private String counselStateCode;
    //TODO 작업중
    private long commentCnt; //댓글수 FIXME - 댓글기능이후 작업 필요
    private long recommandCnt; //추천수 FIXME - 추천기능이후 작업 필요
    private long stateAdoptedCnt; //채택답변수(작성자의) FIXME - 작성자의 채택된 답변수 조회 기능 추가 작업 필요

    private List<String> images = new ArrayList<>();


    public CounselAnswerListResponse(Counsel counsel, Account me) {
        this.id = counsel.getId();
        this.txt = counsel.getTxt();
//        this.counselStateCode = counsel.getCounselState()!=null ? counsel.getCounselState().name() : "";
//        this.counselStateName = counsel.getCounselState()!=null ? counsel.getCounselState().getName() : "";
        this.gubnName = counsel.getGubn()!=null? counsel.getGubn().getName() : "";
//        this.inputTag = counsel.getInputTag();
//        this.reportCnt = counsel.getReportCnt();
        this.accountId = counsel.getAccount().getId(); // 답변자
        this.isMine = (counsel.getAccount().getId() == me.getId());
        this.time = counsel.getCreatedAt()!=null? TimeUtil.calculateTime(Date.from(counsel.getCreatedAt().toInstant())) : "";
        this.commentTxt = counsel.getCommentTxt();
        this.counselStateCode = counsel.getCounselState().name();
        if(counsel.getImages()!=null){
            if(counsel.getImages().getImage1()!=null) images.add(counsel.getImages().getImage1());
            if(counsel.getImages().getImage2()!=null) images.add(counsel.getImages().getImage2());
            if(counsel.getImages().getImage3()!=null) images.add(counsel.getImages().getImage3());
            if(counsel.getImages().getImage4()!=null) images.add(counsel.getImages().getImage4());
            if(counsel.getImages().getImage5()!=null) images.add(counsel.getImages().getImage5());
        }
    }
}
