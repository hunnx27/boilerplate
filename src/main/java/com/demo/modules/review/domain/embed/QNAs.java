package com.demo.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@Embeddable
public class QNAs {
    @Size(max=1000)
    private String q_1;
    @Size(max=1000)
    private String q_2;
    @Size(max=1000)
    private String q_3;
    @Size(max=1000)
    private String a_1;
    @Size(max=1000)
    private String a_2;
    @Size(max=1000)
    private String a_3;
    @Size(max=1000)
    private String q_1_admin;
    @Size(max=1000)
    private String q_2_admin;
    @Size(max=1000)
    private String q_3_admin;
    @Size(max=1000)
    private String a_1_admin;
    @Size(max=1000)
    private String a_2_admin;
    @Size(max=1000)
    private String a_3_admin;
    private ZonedDateTime adminTxtDt1;
    private ZonedDateTime adminTxtDt2;
    private ZonedDateTime adminTxtDt3;
}
