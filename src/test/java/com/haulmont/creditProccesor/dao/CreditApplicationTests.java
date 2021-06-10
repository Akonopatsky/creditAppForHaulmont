package com.haulmont.creditProccesor.dao;

import com.haulmont.creditProccesor.model.*;
import com.haulmont.creditProccesor.storage.repositities.*;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

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
    @Transactional
    void storageSave() {
       List<Bank> bankList = (List<Bank>) bankRepository.findAll();
         for (Bank bank1 : bankList) {
            List<Client> clientList = new ArrayList<>(bank1.getClientSet());
            System.out.println(clientList);
        }
        System.out.println(bankRepository.findAll());
        System.out.println(clientRepository.findAll());
        System.out.println(creditOfferRepository.findAll());
        System.out.println(creditOfferRepository.findAll());
    }

}
