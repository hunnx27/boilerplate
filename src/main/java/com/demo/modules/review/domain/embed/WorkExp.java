package com.demo.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class WorkExp {
    @Size(max=11)
    private int workExp;
    @Size(max=1)
    private String workExpOpenYn;
}
