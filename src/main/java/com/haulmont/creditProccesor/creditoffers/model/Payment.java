package com.haulmont.creditProccesor.creditoffers.model;

import com.haulmont.creditProccesor.creditoffers.money.MonetaryAmount;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

public class Payment {

    private Date date;

    private MonetaryAmount amountOfPayment;

    private MonetaryAmount amountOfBody;

    private MonetaryAmount amountOfInterest;

    private CreditOffer creditOffer;

}
