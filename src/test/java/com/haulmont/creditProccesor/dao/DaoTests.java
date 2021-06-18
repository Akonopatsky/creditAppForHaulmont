package com.haulmont.creditProccesor.dao;

import com.haulmont.creditProccesor.model.*;
import com.haulmont.creditProccesor.storage.dao.BankDao;
import com.haulmont.creditProccesor.storage.dao.ClientDao;
import com.haulmont.creditProccesor.storage.dao.CreditDao;
import com.haulmont.creditProccesor.storage.dao.CreditOfferDao;
import com.haulmont.creditProccesor.storage.repositities.*;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.spi.AbstractAmountFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.logging.LogManager;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class DaoTests {
    @Autowired
    private CreditOfferDao<CreditOffer> creditOfferDao;
    @Autowired
    private ClientDao<Client> clientDao;
    @Autowired
    private CreditDao<Credit> creditDao;
    @Autowired
    private BankDao<Bank> bankDao;
    @Autowired
    private MoneyFactory factory;

    @Test
    @Transactional
    void storageSave() {
        LogManager.getLogManager().reset();
        Bank bank = new Bank("bank1");
        bankDao.save(bank);
        Money creditAmount =factory.getMoneyOf(100_000);
        Credit credit = new Credit(
                creditAmount,
                120,
                Period.ofMonths(12));
        creditDao.save(credit);
        bank.addCredit(credit);
        Client client = new Client("testClient", "33333 33", "2322214124");
        clientDao.save(client);
        bank.addClient(client);
        bankDao.save(bank);
        CreditOffer creditOffer = new CreditOffer.OfferBuilder()
                .beginDate(LocalDate.of(2021, 5, 29))
                .credit(credit)
                .creditAmount(creditAmount)
                .client(client)
                .payStrategy(new PayStrategyAnnuity())
                .build();
        Money monthAmount = factory.getMoneyOf(14676.33);
        List<Payment> paymentList = creditOffer.getPaymentList();
        creditOfferDao.save(creditOffer);
        List<CreditOffer> creditOfferList = creditOfferDao.findAll();
    }

}
