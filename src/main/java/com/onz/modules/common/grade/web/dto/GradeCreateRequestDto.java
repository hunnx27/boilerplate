package com.onz.modules.common.grade.web.dto;

import com.onz.common.web.dto.response.enums.YN;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GradeCreateRequestDto {
    private String grade;
    private Long startTot;
    private Long endTot;
    private Long replayRate;
    private Long replyCnt;
    private YN useYn;
}
