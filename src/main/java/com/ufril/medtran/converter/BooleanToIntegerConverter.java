package com.ufril.medtran.converter;

import javax.persistence.AttributeConverter;

/**
 * @author moin
 *
 */
public class BooleanToIntegerConverter implements AttributeConverter<Boolean, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? 1 : 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        return dbData != null && (dbData > 0);
    }

}
