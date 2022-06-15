package com.onz.modules.review.domain;

import com.onz.modules.common.domain.BaseEntity;
import com.onz.modules.review.domain.embed.*;
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

    public InterviewReview() {
    }

    public InterviewReview(Long idxNo, Review review, Items items, QNAs qNAs) {
        this.idxNo = idxNo;
        this.review = review;
        this.items = items;
        this.qNAs = qNAs;
    }
}
