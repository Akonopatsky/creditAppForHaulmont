package com.haulmont.creditProccesor.creditoffers.model;

import org.javamoney.moneta.Money;

import java.time.LocalDate;

public class Payment {
    private LocalDate date;
    private Money amountOfPayment;
    private Money amountOfBody;
    private Money amountOfInterest;

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
