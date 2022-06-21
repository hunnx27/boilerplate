package com.onz.modules.education.infra;

import com.onz.modules.education.web.dto.request.EducationSearchRequest;
import com.onz.modules.education.domain.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EducationRepositoryExtension {

    Page<Education> educations(EducationSearchRequest educationSearchRequest, Pageable pageable);

    boolean deleteEducation(Long id);
}
