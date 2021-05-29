package com.haulmont.creditProccesor.creditoffers.model;

import com.haulmont.creditProccesor.creditoffers.money.MonetaryAmount;

import java.util.UUID;

public class Credit {

    private UUID uuid;

    private MonetaryAmount creditLimit;

    private double interestRate;

    private int CreditTerm;

    public Credit(MonetaryAmount creditLimit, double interestRate) {
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }
}
