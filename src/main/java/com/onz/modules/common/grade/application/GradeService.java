package com.onz.modules.common.grade.application;

import com.onz.common.util.FileUtil;
import com.onz.common.util.dto.AttachDto;
import com.onz.modules.common.code.domain.CommonCode;
import com.onz.modules.common.code.web.dto.CommonCodeInitResponseDto;
import com.onz.modules.common.grade.domain.Grade;
import com.onz.modules.common.grade.infra.GradeRepository;
import com.onz.modules.common.grade.web.dto.GradeCreateRequestDto;
import com.onz.modules.common.grade.web.dto.GradeListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class GradeService {
    private final GradeRepository gradeRepository;
    private final FileUtil fileUtil;

    public void create(GradeCreateRequestDto gradeCreateRequestDto) {
        Grade grade = new Grade(gradeCreateRequestDto);
        grade.setCreateDt(ZonedDateTime.now());
        if (gradeCreateRequestDto.getIconUrl() != null && gradeCreateRequestDto.getIconUrl().size() > 0) {
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(gradeCreateRequestDto.getIconUrl(), grade.getId());
                grade.setIconUrl((rs.get(0).getSaveName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        gradeRepository.save(grade);
    }

    public List<GradeListResponseDto> list() {
        List<Grade> grades = gradeRepository.findAll();
        return grades.stream().map(GradeListResponseDto::new).collect(Collectors.toList());
    }
}
