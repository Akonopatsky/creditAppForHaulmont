package com.haulmont.creditProccesor.web.dto;

import java.util.List;

public class CreditOfferDto {

    private String id;
    private String client;
    private CreditDto credit;
    private double creditAmount;
    private List<PaymentDto> paymentList;
}
