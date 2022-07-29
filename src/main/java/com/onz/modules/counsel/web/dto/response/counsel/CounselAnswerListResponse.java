package com.onz.modules.counsel.web.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onz.common.util.TimeUtil;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.Counsel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    //TODO 작업중
    private long commentCnt; //댓글수 FIXME - 댓글기능이후 작업 필요
    private long recommandCnt; //추천수 FIXME - 추천기능이후 작업 필요
    private long stateAdoptedCnt; //채택답변수(작성자의) FIXME - 작성자의 채택된 답변수 조회 기능 추가 작업 필요


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
        this.time = counsel.getCounselState()!=null? TimeUtil.calculateTime(Date.from(counsel.getCreatedAt().toInstant())) : "";
    }
}
