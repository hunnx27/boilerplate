package com.onz.modules.review.web.dto;

import com.onz.common.domain.BaseEntity;
import com.onz.common.web.dto.response.enums.YN;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InterviewRequestDto extends BaseEntity {

    //1 page
    private List<Interview> interviews;
//    private Long interviewOrder; // 인터뷰 질문 내림차순정렬
    private String txtAdmin; //관리자 의견
//    private Long reviewOrder;
    private Long companyId;
    private Long workExp;
    private String item_1;
    private YN item_2;
    private YN item_3;
    private String item_4;
    private String item_5;
    private YN item_6;
    private YN workExpOpenYn;


    @Getter
    @Setter
    static public class Interview{
        String q;
        String a;
        String Aq;
        String Aa;
    }
}



// for -> q 기준으로 i 값 answer -> map을 묶어서
