package com.onz.modules.common.code.domain;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.common.code.web.dto.CommonCodeInitRequestDto;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CommonCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(unique = true)
    private Long id;

    private String code;

    private String codeSebu;

    private String codeName;

    private ZonedDateTime createDt;


    private String bigo;

    public CommonCode(CommonCodeInitRequestDto commonCodeInitResponseDto) {
        this.id = id;
        this.code = code;
        this.codeSebu = codeSebu;
        this.codeName = codeName;
        this.createDt = createDt;
        this.bigo = bigo;
    }
}
