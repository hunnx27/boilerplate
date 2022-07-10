package com.onz.modules.account.domain.enums;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class GubnConverter implements AttributeConverter<Gubn, String> {
    @Override
    public String convertToDatabaseColumn(Gubn gubn) {
        String gubnCode = "";
        if(gubn != null){
            gubnCode = gubn.getCode();
        }
        return gubnCode;
    }

    @Override
    public Gubn convertToEntityAttribute(String dbData) {
        Gubn gubn = null;
        if(dbData != null){
            gubn = EnumSet.allOf(Gubn.class).stream()
                    .filter(e->e.getCode().equals(dbData))
                    .findAny()
                    .orElseThrow(()-> new NoSuchElementException());
        }
        return gubn;
    }
}
