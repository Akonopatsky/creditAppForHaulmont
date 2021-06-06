package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.dao.Entities.*;
import com.haulmont.creditProccesor.web.dto.*;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DtoMapper {
    BankEntity saveForm(BankDto bankDto) {
        return new BankEntity(bankDto.getName());
    }

    BankDto findById(BankEntity bankEntity) {
        BankDto bankDto = new BankDto();
        bankDto.setId(bankEntity.getId().toString());
        bankDto.setName(bankEntity.getName());
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

    CreditDto findById(CreditEntity creditEntity) {
        CreditDto creditDto = new CreditDto();
        creditDto.setId(creditEntity.getId().toString());
        creditDto.setCreditLimit(creditEntity.getCreditLimit().doubleValue());
        creditDto.setInterestRate(creditEntity.getInterestRate());
        creditDto.setPeriod(creditEntity.getPeriod());
        return creditDto;
    }

    CreditOfferDto findById(CreditOffer creditOffer) {
        CreditOfferDto creditOfferDto = new CreditOfferDto();
        creditOfferDto.setId(creditOffer.getId().toString());
        creditOfferDto.setClient(findById(creditOffer.getClient()));
        creditOfferDto.setCredit(findById(creditOffer.getCredit()));
        creditOfferDto.setCreditAmount(creditOffer.getCreditAmount().doubleValue());
        creditOfferDto.setPaymentList(creditOffer.getPaymentList().stream()
                .map(payment -> findById(payment))
                .collect(Collectors.toList()));
        return creditOfferDto;
    }

    private PaymentDto findById(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmountOfBody(payment.getAmountOfBody().doubleValue());
        paymentDto.setAmountOfInterest(payment.getAmountOfInterest().doubleValue());
        paymentDto.setAmountOfPayment(payment.getAmountOfPaymant().doubleValue());
        paymentDto.setDate(payment.getDate());
        return paymentDto;
    }

    public Set<BankDto> findAll(Set<BankEntity> bankEntitySet) {
        return bankEntitySet.stream().map(bankEntity -> findById(bankEntity)).collect(Collectors.toSet());
    }

}
