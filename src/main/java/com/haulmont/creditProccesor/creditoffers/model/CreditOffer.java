package com.haulmont.creditProccesor.creditoffers.model;

import org.javamoney.moneta.Money;
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
    private Money creditAmount;
    private List<Payment> paymentList;

    public CreditOffer(OfferBuilder offerBuilder) {
        this.client = offerBuilder.client;
        this.credit = offerBuilder.credit;
        this.creditAmount = offerBuilder.creditAmount;
        paymentList = offerBuilder.paymentList;
    }

    public static Logger getLogger() {
        return logger;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Client getClient() {
        return client;
    }

    public Credit getCredit() {
        return credit;
    }

    public Money getCreditAmount() {
        return creditAmount;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public static class OfferBuilder {
        private Client client;
        private Credit credit;
        private Money creditAmount;
        private LocalDate beginDate;
        private List<Payment> paymentList;

        public OfferBuilder() {
            super();
        }

        public OfferBuilder beginDate(LocalDate date) {
            this.beginDate = date;
            return this;
        }

        public OfferBuilder client(Client client) {
            this.client = client;
            return this;
        }

        public OfferBuilder credit(Credit credit) {
            this.credit = credit;
            return this;
        }

        public OfferBuilder creditAmount(Money amount) {
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
            Money monthPayment = creditAmount.multiply(coeff);
            Money body = Money.from(creditAmount);
            for (int i = 0; i < period-1; i++) {
                Money interest = body.multiply(rate);
                Money bodyPayment  = monthPayment.subtract(interest);
                paymentList.add(i, new Payment(beginDate.plusMonths(i), monthPayment, bodyPayment, interest));
                body = body.subtract(bodyPayment);
            }
            Money interest = body.multiply(rate);
            paymentList.add(period-1, new Payment(beginDate.plusMonths(period), body.add(interest), body, interest));
            return paymentList;
        }

        private boolean validateBuilder() {
            return (client != null
                    && credit != null
                    && creditAmount != null
                    && beginDate != null);
        }
    }
}
