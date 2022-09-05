package com.onz.modules.review.web.dto;

import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.InterviewReviewItem;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class InterviewReviewDetailResponseDto {
    private List<String> itemTest1;
    private String itemTest2;
    private String itemTest3;
    private String itemMood;
    private List<InterviewItemResponse> qna;
    private Long workExp;

    public InterviewReviewDetailResponseDto(InterviewReview interviewReview) {
        if (interviewReview.getItem_1()!= null) {
            itemTest1 = new ArrayList<>();
            for (int i=0; i<interviewReview.getItem_1().length();i++) {
                if (interviewReview.getItem_1().charAt(i) == '1') {
                    itemTest1.add("-제작한 교구보여주기");
                }
                if (interviewReview.getItem_1().charAt(i) == '2') {
                    itemTest1.add("-피아노 연주하기");
                }
                if (interviewReview.getItem_1().charAt(i) == '3') {
                    itemTest1.add("-수업하기");
                }
            }
        }
        this.itemTest2 = interviewReview.getItem_2()!=null&&"Y".equals(interviewReview.getItem_2().name())? "있어요" : "없어요";
        this.itemTest3 = interviewReview.getItem_3()!=null&&"Y".equals(interviewReview.getItem_3().name())? "있어요" : "없어요";
        switch(interviewReview.getItem_5()!=null?interviewReview.getItem_5():"999"){
            case "1": this.itemMood = "-여유";break;
            case "2": this.itemMood = "-편안";break;
            case "3": this.itemMood = "-긴장";break;
            default: this.itemMood = "-";break;
        }
        this.workExp = interviewReview.getWorkExp();
        this.qna= interviewReview.getInterviewItems().stream().map(e -> new InterviewItemResponse(e)).collect(Collectors.toList());

    }

    @Getter
    class InterviewItemResponse{
        private String interviewQ;
        private String interviewA;
        private String interviewAQ;
        private String interviewAA;

        public InterviewItemResponse(InterviewReviewItem item) {
            this.interviewQ = item.getInterviewQ();
            this.interviewA = item.getInterviewA();
            this.interviewAQ = item.getInterviewAQ();
            this.interviewAA = item.getInterviewAA();
        }
    }
}
