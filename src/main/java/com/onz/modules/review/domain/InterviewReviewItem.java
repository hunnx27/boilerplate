package com.onz.modules.review.domain;

import com.onz.modules.admin.reviews.web.dto.InterviewReviewResponseDto;
import com.onz.modules.review.web.dto.InterviewRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class InterviewReviewItem {
    private String interviewQ;
    private String interviewA;
    private String interviewAQ;
    private String interviewAA;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interviewId")
    private InterviewReview interviewReview;

    public InterviewReviewItem(InterviewRequestDto.Interview interview, InterviewReview interviewMom) {
        this.interviewQ = interview.getQ();
        this.interviewA = interview.getA();
        this.interviewAQ= interview.getAq();
        this.interviewAA= interview.getAa();
        this.interviewReview= interviewMom;
    }


}
