package com.onz.modules.review.web.dto;

import com.onz.common.domain.BaseEntity;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.review.domain.enums.ItemCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyReviewRequestDto extends BaseEntity {

    List<MultipartFile> files;

    private String txt;
    private Long companyId;
    private Long workExp;
    private YN WorkExpOpenYn;
    private String itemB1;
    private String itemB2;
    private String itemB3;
    private String itemC1;
    private String itemC2;
    private String itemC3;
    private String itemD1;
    private String itemD2;
    private String likeCode;

}
