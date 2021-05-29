package com.haulmont.creditProccesor.creditoffers.model;

import com.haulmont.creditProccesor.creditoffers.money.MonetaryAmount;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CreditOffer {

    private UUID uuid;

    private Client client;

    private Credit credit;

    private MonetaryAmount creditAmount;

    private List<Payment> paymentList;

    

    public static class Payment {

        private Date date;

        private MonetaryAmount amountOfPayment;

        private MonetaryAmount amountOfBody;

        private MonetaryAmount amountOfInterest;

        private CreditOffer creditOffer;

    }
}
