package com.haulmont.creditProccesor.creditoffers.model;

import org.javamoney.moneta.Money;

import java.time.LocalDate;

public class Payment {
    private final LocalDate date;
    private final Money amountOfPayment;
    private final Money amountOfBody;
    private final Money amountOfInterest;

    public Payment(LocalDate date, Money amountOfPayment, Money amountOfBody, Money amountOfInterest) {
        this.date = date;
        this.amountOfPayment = amountOfPayment;
        this.amountOfBody = amountOfBody;
        this.amountOfInterest = amountOfInterest;
    }

    public LocalDate getDate() {
        return date;
    }

    public Money getAmountOfPayment() {
        return amountOfPayment;
    }

    public Money getAmountOfBody() {
        return amountOfBody;
    }

    public Money getAmountOfInterest() {
        return amountOfInterest;
    }
}
