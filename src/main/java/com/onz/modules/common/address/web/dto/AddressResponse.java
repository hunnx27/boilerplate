package com.onz.modules.common.address.web.dto;

import com.onz.modules.common.address.domain.AddressCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
    private int sidoCode;
    private String sidoName;
    private int sigunguCode;
    private String sigunguName;

    public AddressResponse(AddressCode addressCode) {
        this.sidoCode = addressCode.getSidoCode();
        this.sidoName = addressCode.getSidoName();
        this.sigunguCode = addressCode.getSigunguCode();
        this.sigunguName = addressCode.getSigunguName();
    }
}
