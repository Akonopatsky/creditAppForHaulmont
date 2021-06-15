package com.haulmont.creditProccesor.services.mappers;

import com.haulmont.creditProccesor.model.CreditOffer;
import com.haulmont.creditProccesor.model.Payment;
import com.haulmont.creditProccesor.web.dto.CreditOfferDto;
import com.haulmont.creditProccesor.web.dto.PaymentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditOfferMapper {
    private final ClientMapper clientMapper;
    private final CreditMapper creditMapper;

    public CreditOfferMapper(ClientMapper clientMapper, CreditMapper creditMapper) {
        this.clientMapper = clientMapper;
        this.creditMapper = creditMapper;
    }

    public CreditOfferDto convertToDto(CreditOffer creditOffer) {
        CreditOfferDto creditOfferDto = new CreditOfferDto();
        creditOfferDto.setId(creditOffer.getId().toString());
        creditOfferDto.setClientId(creditOffer.getClient().getId().toString());
        creditOfferDto.setCreditId(creditOffer.getCredit().getId().toString());
        creditOfferDto.setCreditAmount(creditOffer.getCreditAmount()
                .getNumberStripped().doubleValue());
        creditOfferDto.setPaymentList(creditOffer.getPaymentList().stream()
                .map(payment -> convertToDto(payment))
                .collect(Collectors.toList()));
        creditOfferDto.setBeginDate(creditOffer.getBeginDate());
        creditOfferDto.setPayStrategy(creditOffer.getPayStrategyName());
        return creditOfferDto;
    }

    public List<CreditOfferDto> convertToDtoList(List<CreditOffer> creditOfferList) {
        return creditOfferList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private PaymentDto convertToDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmountOfBody(payment.getAmountOfBody().getNumberStripped().doubleValue());
        paymentDto.setAmountOfInterest(payment.getAmountOfInterest().getNumberStripped().doubleValue());
        paymentDto.setAmountOfPayment(payment.getAmountOfPayment().getNumberStripped().doubleValue());
        paymentDto.setDate(payment.getDate());
        return paymentDto;
    }




}
