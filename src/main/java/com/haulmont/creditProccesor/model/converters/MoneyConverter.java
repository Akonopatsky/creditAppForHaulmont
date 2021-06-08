package com.haulmont.creditProccesor.model.converters;

import org.javamoney.moneta.Money;

import javax.persistence.AttributeConverter;

public class MoneyConverter implements AttributeConverter<Money, Double> {
    @Override
    public Double convertToDatabaseColumn(Money attribute) {
        return attribute.getNumberStripped().doubleValue();
    }

    @Override
    public Money convertToEntityAttribute(Double dbData) {
        return Money.of(dbData, "RUB");
    }
}
