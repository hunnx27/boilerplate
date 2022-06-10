package com.demo.modules.review.domain;

import com.demo.modules.common.domain.BaseEntity;
import com.demo.modules.review.domain.embed.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class InterviewReview extends BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Size(max=50)
    private Long idxNo;

    @Embedded
    Review review;
    @Embedded
    private Items items;
    @Embedded
    private QNAs qNAs;
}
