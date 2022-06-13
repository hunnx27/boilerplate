package com.demo.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@Embeddable
public class Amt {
    @Size(max=11)
    private int amt;
    @Size(max=11)
    private int amtOld;

    public Amt() {
    }

    public Amt(int amt, int amtOld) {
        this.amt = amt;
        this.amtOld = amtOld;
    }
}
