package com.onz.modules.counsel.web.dto.request.counsel;

import com.onz.common.enums.Gubn;
import com.onz.common.enums.InterestCompany;
import com.onz.common.enums.YN;
import com.onz.modules.counsel.domain.enums.QnaItem;
import com.onz.modules.counsel.web.dto.request.counsel.enums.CounselSearchType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CounselSearchRequest {

    private CounselSearchType counselSearchType;
    private String keyword;
    private Gubn gubn;

}
