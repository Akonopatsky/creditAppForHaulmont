package com.haulmont.creditProcessor.model;

import org.javamoney.moneta.Money;

import java.math.BigDecimal;

public class MoneyFactoryImpl implements MoneyFactory {
    private final String currencyCode;

    public MoneyFactoryImpl(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public Money getMoneyOf(double number) {
        return Money.of(number, currencyCode);
    }

    @Override
    public Money getMoneyOf(Number number) {
        return Money.of(number, currencyCode);
    }

    @Override
    public Money getMoneyOf(BigDecimal number) {
        return Money.of(number, currencyCode);
    }
}
