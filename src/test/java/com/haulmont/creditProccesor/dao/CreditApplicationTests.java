package com.haulmont.creditProccesor.dao;

import com.haulmont.creditProccesor.model.*;
import com.haulmont.creditProccesor.storage.repositities.*;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Period;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CreditApplicationTests {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private CreditOfferRepository creditOfferRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void storageSave() {
        Bank bank = new Bank("bank 1");
        bankRepository.save(bank);
        Client client1 = new Client("Client 1", "111 111  111", "1111 111111");
        Client client2 = new Client("Client 2", "222 222  222", "2222 222222");
        clientRepository.save(client1);
        clientRepository.save(client2);
        bank.addClient(client1);
        bank.addClient(client2);
        Credit credit1 = new Credit(Money.of(1111, "RUB"), 0.11d, Period.ofMonths(11));
        Credit credit2 = new Credit(Money.of(2222, "RUB"), 0.22d, Period.ofMonths(22));
        creditRepository.save(credit1);
        creditRepository.save(credit2);
        bank.addCredit(credit1);
        bank.addCredit(credit2);
        CreditOffer creditOffer = new CreditOffer.OfferBuilder()
                .payStrategy(new PayStrategyAnnuity())
                .credit(credit2)
                .creditAmount(Money.of(2221, "RUB"))
                .client(client2)
                .beginDate(LocalDate.of(2021, 06, 06))
                .build();
        paymentRepository.saveAll(creditOffer.getPaymentList());
        creditOfferRepository.save(creditOffer);
        System.out.println(bankRepository.findAll());
        System.out.println(clientRepository.findAll());
        System.out.println(creditOfferRepository.findAll());
        System.out.println(creditOfferRepository.findAll());
    }

}
