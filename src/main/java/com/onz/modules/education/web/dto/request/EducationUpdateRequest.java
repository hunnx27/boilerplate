package com.onz.modules.education.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EducationUpdateRequest {

    @Setter
    private Long id;
    private String name;
    private String subject;
}
