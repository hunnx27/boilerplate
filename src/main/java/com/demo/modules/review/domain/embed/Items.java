package com.demo.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class Items {
    @Size(max=10)
    private String item_1;
    @Size(max=10)
    private String item_2;
    @Size(max=10)
    private String item_3;
    @Size(max=10)
    private String item_4;
    @Size(max=10)
    private String item_5;
    @Size(max=10)
    private String item_6;
}
