package com.onz.modules.company.web.dto.reponse;

import com.onz.common.domain.BaseEntity;
import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.web.dto.AddressResponse;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.domain.enums.EstablishmentType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
public class CompanySearchResponse {
    //히든값 id
    //officename
    //zonecode
    private String officeName;
    private String zonecode;
    private String sidoName;
    private String gubuName;
    private String mapsidogunguName;
    private String establishName;
    @JsonIgnore
    private Long id;

    public CompanySearchResponse(String officeName, String zonecode, Long id,EstablishmentType establishmentType) {
        this.id = id;
        this.establishName= establishmentType.getName();
        this.officeName = officeName;
        this.zonecode = zonecode;
    }


}
