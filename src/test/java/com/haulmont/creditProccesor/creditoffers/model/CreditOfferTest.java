package com.haulmont.creditProccesor.creditoffers.model;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.Monetary;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.logging.LogManager;

import static org.junit.jupiter.api.Assertions.*;

class CreditOfferTest {

    @Test
    void creationTest() {
        LogManager.getLogManager().reset();
        Money creditAmount = Money.of(100_000, "RUB");
        Credit credit = new Credit(
                creditAmount,
                120,
                Period.ofMonths(12));
        Client client = new Client("testClient", "33333 33", "2322214124");
        CreditOffer creditOffer = new CreditOffer.OfferBuilder()
                .beginDate(LocalDate.of(2021, 5, 29))
                .credit(credit)
                .creditAmount(creditAmount)
                .client(client)
                .build();
        Money monthAmount = Money.of(14676.33, "RUB");
        List<Payment> paymentList =creditOffer.getPaymentList();
        for (int i = 0; i < paymentList.size(); i++) {
            System.out.print(i+ " " + creditOffer.getPaymentList().get(i).getAmountOfPayment() + " !!!");
            System.out.print(creditOffer.getPaymentList().get(i).getAmountOfBody() + " ");
            System.out.println(creditOffer.getPaymentList().get(i).getAmountOfInterest());
        }

        assertTrue(monthAmount.toString().equals(calculated.toString()));

    }

}