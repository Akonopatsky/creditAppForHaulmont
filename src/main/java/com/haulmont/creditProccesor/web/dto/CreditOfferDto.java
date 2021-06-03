package com.haulmont.creditProccesor.web.dto;

import java.util.List;

public class CreditOfferDto {

    private String uuid;
    private String client;
    private String credit;
    private double creditAmount;
    private List<PaymentDto> paymentList;
}
