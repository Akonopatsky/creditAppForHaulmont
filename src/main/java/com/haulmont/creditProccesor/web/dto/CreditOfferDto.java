package com.haulmont.creditProccesor.web.dto;

import java.util.List;

public class CreditOfferDto {
    private String id;
    private String payStrategy;
    private ClientDto client;
    private CreditDto credit;
    private double creditAmount;
    private List<PaymentDto> paymentList;

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

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public CreditDto getCredit() {
        return credit;
    }

    public void setCredit(CreditDto credit) {
        this.credit = credit;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public List<PaymentDto> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<PaymentDto> paymentList) {
        this.paymentList = paymentList;
    }
}
