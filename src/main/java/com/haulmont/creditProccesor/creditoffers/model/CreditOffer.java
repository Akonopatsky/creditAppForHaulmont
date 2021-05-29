package com.haulmont.creditProccesor.creditoffers.model;

import com.haulmont.creditProccesor.creditoffers.money.MonetaryAmount;

import java.util.List;
import java.util.UUID;

public class CreditOffer {

    private UUID uuid;

    private Client client;

    private Credit credit;

    private MonetaryAmount creditAmount;

    private List<Payment> paymentList;
}
