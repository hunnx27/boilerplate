package com.demo.modules.review.domain.embed;

import com.demo.modules.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class Review extends BaseEntity {
    @Size(max=50)
    private String id;
    @Size(max=11)
    private int code;
    @Size(max=1)
    private String state;
    @Embedded
    private WorkExp workExp;
    @Embedded
    private Topchoice topchoice;
    @Embedded
    private Appr appr;
}
