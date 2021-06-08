package com.haulmont.creditProccesor.creditoffers.model;

import com.haulmont.creditProccesor.model.*;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.logging.LogManager;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .payStrategy(new PayStrategyAnnuity())
                .build();
        Money monthAmount = Money.of(14676.33, "RUB");
        List<Payment> paymentList = creditOffer.getPaymentList();
        List<Money> bodyAmountPaymentList = Stream.of(
                Money.of(4676.33, "RUB"),
                Money.of(5143.96, "RUB"),
                Money.of(5658.36, "RUB"),
                Money.of(6224.19, "RUB"),
                Money.of(6846.61, "RUB"),
                Money.of(7531.27, "RUB"),
                Money.of(8284.4, "RUB"),
                Money.of(9112.84, "RUB"),
                Money.of(10024.13, "RUB"),
                Money.of(11026.54, "RUB"),
                Money.of(12129.19, "RUB"),
                Money.of(13342.18, "RUB"))
                .collect(Collectors.toList());

        for (int i = 0; i < paymentList.size() - 1; i++) {
            assertTrue(creditOffer.getPaymentList().get(i).getAmountOfBody()
                    .subtract(bodyAmountPaymentList.get(i)).isLessThan(Money.of(0.02, "RUB")));
        }
        int period = creditOffer.getPaymentList().size();
        assertTrue(
                creditOffer.getPaymentList().get(period-1).getAmountOfBody()
                        .subtract(bodyAmountPaymentList.get(period - 1)).abs()
                        .isLessThan(Money.of(0.01, "RUB").multiply(period)));
    }
}