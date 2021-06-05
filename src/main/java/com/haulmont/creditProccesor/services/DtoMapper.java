package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.business.model.CreditOffer;
import com.haulmont.creditProccesor.dao.Entities.*;
import com.haulmont.creditProccesor.web.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
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

    ClientDto findById(ClientEntity clientEntity) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(clientEntity.getId().toString());
        clientDto.setName(clientEntity.getName());
        clientDto.setPhoneNumber(clientEntity.getPhoneNumber());
        clientDto.setPassportNumber(clientEntity.getPassportNumber());
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

    CreditOfferDto findById(CreditOfferEntity creditOfferEntity) {
        CreditOfferDto creditOfferDto = new CreditOfferDto();
        creditOfferDto.setId(creditOfferEntity.getId().toString());
        creditOfferDto.setClient(findById(creditOfferEntity.getClient()));
        creditOfferDto.setCredit(findById(creditOfferEntity.getCredit()));
        creditOfferDto.setCreditAmount(creditOfferEntity.getCreditAmount().doubleValue());
        creditOfferDto.setPaymentList(creditOfferEntity.getPaymentList().stream()
                .map(paymentEntity -> findById(paymentEntity))
                .collect(Collectors.toList()));
        return creditOfferDto;
    }

    private PaymentDto findById(PaymentEntity paymentEntity) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmountOfBody(paymentEntity.getAmountOfBody().doubleValue());
        paymentDto.setAmountOfInterest(paymentEntity.getAmountOfInterest().doubleValue());
        paymentDto.setAmountOfPayment(paymentEntity.getAmountOfPaymant().doubleValue());
        paymentDto.setDate(paymentEntity.getDate());
        return paymentDto;
    }

    public Set<BankDto> findAll(Set<BankEntity> bankEntitySet) {
        return bankEntitySet.stream().map(bankEntity -> findById(bankEntity)).collect(Collectors.toSet());
    }

}
