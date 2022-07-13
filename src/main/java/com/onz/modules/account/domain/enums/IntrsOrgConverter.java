package com.onz.modules.account.domain.enums;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class IntrsOrgConverter implements AttributeConverter<IntrsOrg, String> {
    @Override
    public String convertToDatabaseColumn(IntrsOrg intrsOrg) {
        String intrsOrgCoe = "";
        if(intrsOrg != null){
            intrsOrgCoe = intrsOrg.getCode();
        }
        return intrsOrgCoe;
    }

    @Override
    public IntrsOrg convertToEntityAttribute(String dbData) {
        IntrsOrg intrsOrg = null;
        if( dbData != null && !"".equals(dbData) ){
            intrsOrg = EnumSet.allOf(IntrsOrg.class).stream()
                    .filter(e->e.getCode().equals(dbData))
                    .findAny()
                    .orElseThrow(()-> new NoSuchElementException());
        }
        return intrsOrg;
    }
}
