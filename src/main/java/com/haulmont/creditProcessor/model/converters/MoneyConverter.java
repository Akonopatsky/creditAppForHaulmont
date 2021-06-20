package com.haulmont.creditProcessor.model.converters;

import com.haulmont.creditProcessor.model.MoneyFactory;
import org.javamoney.moneta.Money;

import javax.persistence.AttributeConverter;

public class MoneyConverter implements AttributeConverter<Money, Double> {
    private final MoneyFactory moneyFactory;

    public MoneyConverter(MoneyFactory moneyFactory) {
        this.moneyFactory = moneyFactory;
    }

    @Override
    public Double convertToDatabaseColumn(Money attribute) {
        return attribute.getNumberStripped().doubleValue();
    }

    @Override
    public Money convertToEntityAttribute(Double dbData) {
        return moneyFactory.getMoneyOf(dbData);
    }
}
