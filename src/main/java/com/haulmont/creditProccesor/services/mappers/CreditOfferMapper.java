package com.haulmont.creditProccesor.services.mappers;

import com.haulmont.creditProccesor.model.CreditOffer;
import com.haulmont.creditProccesor.model.Payment;
import com.haulmont.creditProccesor.web.dto.CreditOfferDto;
import com.haulmont.creditProccesor.web.dto.PaymentDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CreditOfferMapper {
    private final ClientMapper clientMapper;
    private final CreditMapper creditMapper;

    public CreditOfferMapper(ClientMapper clientMapper, CreditMapper creditMapper) {
        this.clientMapper = clientMapper;
        this.creditMapper = creditMapper;
    }

    public CreditOfferDto getById(CreditOffer creditOffer) {
        CreditOfferDto creditOfferDto = new CreditOfferDto();
        creditOfferDto.setId(creditOffer.getId().toString());
        creditOfferDto.setClient(clientMapper.convertToDto(creditOffer.getClient()));
        creditOfferDto.setCredit(creditMapper.convertToDto(creditOffer.getCredit()));
        creditOfferDto.setCreditAmount(creditOffer.getCreditAmount()
                .getNumberStripped().doubleValue());
        creditOfferDto.setPaymentList(creditOffer.getPaymentList().stream()
                .map(payment -> getById(payment))
                .collect(Collectors.toList()));
        return creditOfferDto;
    }

    private PaymentDto getById(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmountOfBody(payment.getAmountOfBody().getNumberStripped().doubleValue());
        paymentDto.setAmountOfInterest(payment.getAmountOfInterest().getNumberStripped().doubleValue());
        paymentDto.setAmountOfPayment(payment.getAmountOfPayment().getNumberStripped().doubleValue());
        paymentDto.setDate(payment.getDate());
        return paymentDto;
    }

}
