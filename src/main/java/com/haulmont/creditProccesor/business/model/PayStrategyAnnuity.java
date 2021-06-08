package com.haulmont.creditProccesor.business.model;

import org.javamoney.moneta.Money;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayStrategyAnnuity implements PayStrategy {
    @Override
    public List<Payment> calculate(Credit credit, Money amount, LocalDate date) {
        double rate = credit.getInterestRate()/1200d;
        int quantity = credit.getPeriod().getMonths();
        List<Payment> paymentList = new ArrayList<>(quantity);
        double coeff = (rate)/(1-Math.pow(1+rate, -quantity));
        Money monthPayment = amount.multiply(coeff);
        Money body = Money.from(amount);
        for (int i = 0; i < quantity-1; i++) {
            Money interest = body.multiply(rate);
            Money bodyPayment  = monthPayment.subtract(interest);
            paymentList.add(i, new Payment(date.plusMonths(i), monthPayment, bodyPayment, interest));
            body = body.subtract(bodyPayment);
        }
        Money interest = body.multiply(rate);
        paymentList.add(quantity-1, new Payment(date.plusMonths(quantity), body.add(interest), body, interest));
        return paymentList;
    }
}
