package com.haulmont.creditProccesor.web.dto;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class PaymentDto {
    private LocalDate date;
    private double amountOfPayment;
    private double amountOfBody;
    private double amountOfInterest;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmountOfPayment() {
        return amountOfPayment;
    }

    public void setAmountOfPayment(double amountOfPayment) {
        this.amountOfPayment = amountOfPayment;
    }

    public double getAmountOfBody() {
        return amountOfBody;
    }

    public void setAmountOfBody(double amountOfBody) {
        this.amountOfBody = amountOfBody;
    }

    public double getAmountOfInterest() {
        return amountOfInterest;
    }

    public void setAmountOfInterest(double amountOfInterest) {
        this.amountOfInterest = amountOfInterest;
    }

    @Override
    public String toString() {
        DecimalFormat dF = new DecimalFormat("#.##");
        return "Payment " +
                "date=" + date +
                "  amountOfPayment=" + dF.format(amountOfPayment) +
                "  amountOfBody=" + dF.format(amountOfBody) +
                "  amountOfInterest=" + dF.format(amountOfInterest);
    }
}
