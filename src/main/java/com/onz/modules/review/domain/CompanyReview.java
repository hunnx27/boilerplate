package com.onz.modules.review.domain;

import com.onz.common.domain.BaseEntity;
import com.onz.modules.review.domain.embed.AdminTxt;
import com.onz.modules.review.domain.embed.Images;
import com.onz.modules.review.domain.embed.Review;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class CompanyReview extends BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Size(max=50)
    private Long idxNo;

    @Embedded
    Review review;
    @Embedded
    private AdminTxt adminTxt;
    @Embedded
    private Images images;

    @Size(max=1000)
    private String txt;
    @Size(max=2)
    private String grade;
    @Size(max=200)
    private String reviewJumsu;

    public CompanyReview() {
    }

    public CompanyReview(Long idxNo, Review review, AdminTxt adminTxt, Images images, String txt, String grade, String reviewJumsu) {
        this.idxNo = idxNo;
        this.review = review;
        this.adminTxt = adminTxt;
        this.images = images;
        this.txt = txt;
        this.grade = grade;
        this.reviewJumsu = reviewJumsu;
    }
}
