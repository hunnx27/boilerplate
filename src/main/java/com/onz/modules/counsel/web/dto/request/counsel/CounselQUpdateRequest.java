package com.onz.modules.counsel.web.dto.request.counsel;

import com.onz.common.enums.InterestOrg;
import com.onz.common.enums.YN;
import com.onz.modules.counsel.domain.enums.QnaItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CounselQUpdateRequest {

    private Long id;
    List<MultipartFile> files;
    private String addedTagData;
    private InterestOrg interestOrgName; // 관심 기관
    private String relatedZone;
    private QnaItem qnaItem;
    private String txt;
    private YN shortOpenYn;

    // 자동처리
//    @Convert(converter = GubnConverter.class)
//    private Gubn gubn;
//    private JobGubn jobGubn;
//    private QnaGubn qnaGubn=QnaGubn.Q;





}
