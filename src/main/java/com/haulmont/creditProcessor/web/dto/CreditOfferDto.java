package com.haulmont.creditProcessor.web.dto;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public class CreditOfferDto {

    private String id;
    private String payStrategy;
    private String clientId;
    private String creditId;
    private double creditAmount;
    private LocalDate beginDate;
    private List<PaymentDto> paymentList;

    public String getInterest() {
        DecimalFormat dF = new DecimalFormat("#.##");
        return dF.format(paymentList.stream().mapToDouble(payment -> payment.getAmountOfInterest()).sum());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayStrategy() {
        return payStrategy;
    }

    public void setPayStrategy(String payStrategy) {
        this.payStrategy = payStrategy;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public List<PaymentDto> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<PaymentDto> paymentList) {
        this.paymentList = paymentList;
    }

}
