package com.haulmont.creditProccesor.services;


import com.haulmont.creditProccesor.business.model.*;
import com.haulmont.creditProccesor.web.dto.*;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DtoToModelMapper {
    BankDto findById(Bank bank) {
        BankDto bankDto = new BankDto();
        bankDto.setId(bank.getId().toString());
        bankDto.setName(bank.getName());
        return bankDto;
    }

    ClientDto findById(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId().toString());
        clientDto.setName(client.getName());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setPassportNumber(client.getPassportNumber());
        return clientDto;
    }

    CreditDto findById(Credit credit) {
        CreditDto creditDto = new CreditDto();
        creditDto.setId(credit.getId().toString());
        creditDto.setCreditLimit(credit.getCreditLimit().getNumberStripped().doubleValue());
        creditDto.setInterestRate(credit.getInterestRate());
        creditDto.setPeriod(credit.getPeriod().getMonths());
        return creditDto;
    }

    CreditOfferDto findById(CreditOffer creditOffer) {
        CreditOfferDto creditOfferDto = new CreditOfferDto();
        creditOfferDto.setId(creditOffer.getId().toString());
        creditOfferDto.setClient(findById(creditOffer.getClient()));
        creditOfferDto.setCredit(findById(creditOffer.getCredit()));
        creditOfferDto.setCreditAmount(creditOffer.getCreditAmount()
                .getNumberStripped().doubleValue());
        creditOfferDto.setPaymentList(creditOffer.getPaymentList().stream()
                .map(payment -> findById(payment))
                .collect(Collectors.toList()));
        return creditOfferDto;
    }

    private PaymentDto findById(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmountOfBody(payment.getAmountOfBody().getNumberStripped().doubleValue());
        paymentDto.setAmountOfInterest(payment.getAmountOfInterest().getNumberStripped().doubleValue());
        paymentDto.setAmountOfPayment(payment.getAmountOfPayment().getNumberStripped().doubleValue());
        paymentDto.setDate(payment.getDate());
        return paymentDto;
    }

    public Set<BankDto> findAll(Set<Bank> bankEntitySet) {
        return bankEntitySet.stream().map(bankEntity -> findById(bankEntity)).collect(Collectors.toSet());
    }
}
