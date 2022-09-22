package com.onz.modules.review.web.dto;

import com.onz.common.domain.BaseEntity;
import com.onz.common.enums.YN;
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
    private ItemCode itemB2;
    private ItemCode itemB1;
    private ItemCode itemC1;
    private ItemCode itemB3;
    private ItemCode itemC3;
    private ItemCode itemC2;
    private ItemCode itemD1;
    private ItemCode itemD2;
    private ItemCode likeCode;

}
