package com.haulmont.creditProcessor.web.dto;

import java.text.DecimalFormat;

public class CreditDto {
    private String id;

    private double creditLimit;

    private double interestRate;

    private int period;

    private String bankId;

    public CreditDto() {
    }

    public String getCreditLimitFormatted() {
        DecimalFormat dF = new DecimalFormat("#.##");
        return dF.format(this.creditLimit);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}
