package com.haulmont.creditProcessor.model;

import org.javamoney.moneta.Money;

import java.math.BigDecimal;

public interface MoneyFactory {
    Money getMoneyOf(double number);
    Money getMoneyOf(Number number);
    Money getMoneyOf(BigDecimal number);
}
