package com.onz.modules.counsel.web.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.onz.common.enums.*;
import com.onz.modules.account.domain.Account;
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
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class CounselResponse {

    private Long id;
    private String counselStateCode;
    private String counselStateName;
    private String gubnName;
    private String inputTag;
    private String txt;
    private String createDate;
    private long reportCnt;
    private Long accountId;
    @JsonProperty(value="isMine")
    private boolean isMine;

    public CounselResponse(Counsel counsel, Account me) {
        this.id = counsel.getId();
        this.counselStateCode = counsel.getCounselState()!=null ? counsel.getCounselState().name() : "";
        this.counselStateName = counsel.getCounselState()!=null ? counsel.getCounselState().getName() : "";
        this.gubnName = counsel.getGubn()!=null? counsel.getGubn().getName() : "";
        this.inputTag = counsel.getInputTag();
        this.txt = counsel.getTxt();
        this.reportCnt = counsel.getReportCnt();
        this.createDate = counsel.getCreatedAt()!=null? counsel.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) : "";
        this.accountId = counsel.getAccount().getId();
        this.isMine = (counsel.getAccount().getId() == me.getId());
    }
}
