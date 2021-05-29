package com.haulmont.creditProccesor.creditoffers.model;

import com.haulmont.creditProccesor.creditoffers.money.MonetaryAmount;

import java.time.LocalDate;

public class Payment {
    private LocalDate date;
    private MonetaryAmount amountOfPayment;
    private MonetaryAmount amountOfBody;
    private MonetaryAmount amountOfInterest;

    public Payment(LocalDate date, MonetaryAmount amountOfPayment, MonetaryAmount amountOfBody, MonetaryAmount amountOfInterest) {
        this.date = date;
        this.amountOfPayment = amountOfPayment;
        this.amountOfBody = amountOfBody;
        this.amountOfInterest = amountOfInterest;
    }


}
