package com.onz.modules.review.domain;

import com.onz.common.domain.BaseEntity;
import com.onz.modules.review.domain.embed.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class YearAmtReivew extends BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Size(max=50)
    private Long idxNo;

    @Embedded
    Review review;
    @Embedded
    Amt amt;
    @Embedded
    EtcAmt etcAmt;

    @Size(max=1)
    private String annYn;


    public YearAmtReivew() {
    }

    public YearAmtReivew(Long idxNo, Review review, Amt amt, EtcAmt etcAmt, String annYn) {
        this.idxNo = idxNo;
        this.review = review;
        this.amt = amt;
        this.etcAmt = etcAmt;
        this.annYn = annYn;
    }


}
