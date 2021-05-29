package com.haulmont.creditProccesor.creditoffers.money;

public interface MonetaryAmount {
    MonetaryAmount add(MonetaryAmount amount);
    MonetaryAmount subtract(MonetaryAmount amount);
    MonetaryAmount multiply(long amount);
    MonetaryAmount multiply(double amount);
    MonetaryAmount multiply(Number amount);
    MonetaryAmount divide(long amount);
    MonetaryAmount divide(double amount);
    MonetaryAmount divide(Number amount);
}
