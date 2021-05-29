package com.haulmont.creditProccesor.creditoffers.model;

import com.haulmont.creditProccesor.creditoffers.money.MonetaryAmount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreditOffer {
    private static final Logger logger = LoggerFactory.getLogger(CreditOffer.class);

    private UUID uuid;
    private Client client;
    private Credit credit;
    private MonetaryAmount creditAmount;
    private List<Payment> paymentList;

    public CreditOffer(OfferBuilder offerBuilder) {
        this.client = offerBuilder.client;
        this.credit = offerBuilder.credit;
        this.creditAmount = offerBuilder.creditAmount;
        paymentList = offerBuilder.paymentList;
    }

    public static class OfferBuilder {
        private Client client;
        private Credit credit;
        private MonetaryAmount creditAmount;
        private LocalDate beginDate;
        private List<Payment> paymentList;

        public OfferBuilder client(Client client) {
            this.client = client;
            return this;
        }

        public OfferBuilder credit(Credit credit) {
            this.credit = credit;
            return this;
        }

        public OfferBuilder creditAmount(MonetaryAmount amount) {
            this.creditAmount = amount;
            return this;
        }

        public CreditOffer build() {
            CreditOffer creditOffer = null;
            if (validateBuilder()) {
                paymentList = calcPayments();
                creditOffer = new CreditOffer(this);
            } else {
                logger.error("There is no valid data for CreditOffer building");
            }
            return creditOffer;
        }

        private List<Payment> calcPayments(){
            // TODO: 29.05.2021  strategy

            double rate = credit.getInterestRate()/1200d;
            int period = credit.getPeriod().getMonths();
            List<Payment> paymentList = new ArrayList<>(period+1);
            double coeff = (rate)/(1-Math.pow(1+rate, -period));
            MonetaryAmount monthPayment = creditAmount.multiply(coeff);
            MonetaryAmount body = creditAmount.createCopy();
            for (int i = 1; i < period; i++) {
                MonetaryAmount interest = body.multiply(rate);
                MonetaryAmount bodyPayment  = monthPayment.subtract(interest);
                paymentList.add(i, new Payment(beginDate.plusMonths(i), monthPayment, bodyPayment, interest));
                body = body.subtract(monthPayment);
            }
            MonetaryAmount interest = body.multiply(rate);
            paymentList.add(period, new Payment(beginDate.plusMonths(period), body.add(interest), body, interest));
            return paymentList;
        }

        private boolean validateBuilder() {
            return (client != null && credit != null && creditAmount != null);
        }


    }
}
