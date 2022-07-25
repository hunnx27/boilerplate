package com.onz.modules.counsel.web.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.common.enums.*;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.embed.Images;
import com.onz.modules.counsel.domain.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CounselCreateRequest {

    List<MultipartFile> files;
    private String addedTagData;
    private InterestOrg interestOrgName; // 관심 기관
    private String interestZone;
    private QnaItem qnaItem;
    private String txt;
    private YN shortOpenYn;

    // 자동처리
//    @Convert(converter = GubnConverter.class)
//    private Gubn gubn;
//    private JobGubn jobGubn;
//    private QnaGubn qnaGubn=QnaGubn.Q;





}
