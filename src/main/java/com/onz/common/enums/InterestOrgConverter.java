package com.onz.common.enums;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class InterestOrgConverter implements AttributeConverter<InterestOrg, String> {
    @Override
    public String convertToDatabaseColumn(InterestOrg intrsOrg) {
        String intrsOrgCoe = "";
        if(intrsOrg != null){
            intrsOrgCoe = intrsOrg.getCode();
        }
        return intrsOrgCoe;
    }

    @Override
    public InterestOrg convertToEntityAttribute(String dbData) {
        InterestOrg intrsOrg = null;
        if( dbData != null && !"".equals(dbData) ){
            intrsOrg = EnumSet.allOf(InterestOrg.class).stream()
                    .filter(e->e.getCode().equals(dbData))
                    .findAny()
                    .orElseThrow(()-> new NoSuchElementException());
        }
        return intrsOrg;
    }
}
