package com.haulmont.creditProccesor.services.mappers;

import com.haulmont.creditProccesor.storage.Domain.ClientData;
import com.haulmont.creditProccesor.storage.Domain.CreditData;
import com.haulmont.creditProccesor.storage.Domain.CreditOfferData;
import com.haulmont.creditProccesor.storage.Domain.PaymentData;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import com.haulmont.creditProccesor.web.dto.CreditDto;
import com.haulmont.creditProccesor.web.dto.CreditOfferDto;
import com.haulmont.creditProccesor.web.dto.PaymentDto;

import java.util.stream.Collectors;

public class ClientMapper {
    public ClientDto findById(ClientData client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId().toString());
        clientDto.setName(client.getName());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setPassportNumber(client.getPassportNumber());
        return clientDto;
    }

    public CreditOfferDto findById(CreditOfferData creditOffer) {
        CreditOfferDto creditOfferDto = new CreditOfferDto();
        creditOfferDto.setId(creditOffer.getId().toString());
        creditOfferDto.setClient(findById(creditOffer.getClient()));
        creditOfferDto.setCredit(findById(creditOffer.getCredit()));
        creditOfferDto.setCreditAmount(creditOffer.getCreditAmount().doubleValue());

        creditOfferDto.setPaymentList(
                creditOffer.getPaymentList().stream()
                        .map(payment -> findById(payment))
                        .collect(Collectors.toList()));
        return creditOfferDto;
    }

    public CreditDto findById(CreditData creditData) {
        CreditDto creditDto = new CreditDto();
        creditDto.setId(creditData.getId().toString());
        creditDto.setCreditLimit(creditData.getCreditLimit().doubleValue());
        creditDto.setInterestRate(creditData.getInterestRate());
        creditDto.setPeriod(creditData.getPeriod());
        return creditDto;
    }

    private PaymentDto findById(PaymentData payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmountOfBody(payment.getAmountOfBody().doubleValue());
        paymentDto.setAmountOfInterest(payment.getAmountOfInterest().doubleValue());
        paymentDto.setAmountOfPayment(payment.getAmountOfPaymant().doubleValue());
        paymentDto.setDate(payment.getDate());
        return paymentDto;
    }

}
