package com.demo.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class Images {
    @Size(max=200)
    private String image1;
    @Size(max=200)
    private String image2;
    @Size(max=200)
    private String image3;
    @Size(max=200)
    private String image4;
    @Size(max=200)
    private String image5;
}
