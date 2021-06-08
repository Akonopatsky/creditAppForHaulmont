package com.haulmont.creditProccesor.model.converters;

import javax.persistence.AttributeConverter;
import java.time.Period;

public class PeriodConverter implements AttributeConverter<Period, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Period attribute) {
        return attribute.getMonths();
    }

    @Override
    public Period convertToEntityAttribute(Integer dbData) {
        return Period.ofMonths(dbData);
    }
}
