package com.onz.modules.account.domain.enums;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class GubnConverter implements AttributeConverter<Gubn, String> {
    @Override
    public String convertToDatabaseColumn(Gubn gubn) {
        return gubn.getCode();
    }

    @Override
    public Gubn convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(Gubn.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }
}
