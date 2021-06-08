package com.haulmont.creditProccesor.services.mappers;

import com.haulmont.creditProccesor.model.*;
import com.haulmont.creditProccesor.storage.dao.CreditDao;
import com.haulmont.creditProccesor.web.dto.*;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DtoToModelMapper {
/*    private final CreditDao<CreditDto> creditDao;

    public DtoToModelMapper(CreditDao<CreditDto> creditDao) {
        this.creditDao = creditDao;
    }*/

    public Bank getNewBank(BankDto bankDto) {
        return new Bank(bankDto.getName());
    }

    public List<BankDto> getAll(Set<Bank> bankEntitySet) {
        return bankEntitySet.stream().map(bankEntity -> getById(bankEntity)).collect(Collectors.toList());
    }

    public BankDto getById(Bank bank) {
        BankDto bankDto = new BankDto();
        bankDto.setId(bank.getId().toString());
        bankDto.setName(bank.getName());
        return bankDto;
    }

    public Client getNewClient(ClientDto clientDto) {
        return new Client(clientDto.getName(),
                clientDto.getPhoneNumber(),
                clientDto.getPassportNumber());
    }

    public Credit getNewCredit(CreditDto creditDto) {
        return new Credit(Money.of(creditDto.getCreditLimit(), "RUB"),
                creditDto.getCreditLimit(),
                Period.ofMonths(creditDto.getPeriod()));
    }

/*
    public  CreditOffer getNewCreditOffer(CreditOfferDto creditOfferDto) {
        Credit credit = cre
        CreditOffer creditOffer = new CreditOffer.OfferBuilder()
                .credit(getById(UUID.fromString(creditOfferDto.getCredit().getId()));
    }
*/



    public ClientDto getById(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId().toString());
        clientDto.setName(client.getName());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setPassportNumber(client.getPassportNumber());
        return clientDto;
    }

    public CreditDto getById(Credit credit) {
        CreditDto creditDto = new CreditDto();
        creditDto.setId(credit.getId().toString());
        creditDto.setCreditLimit(credit.getCreditLimit().getNumberStripped().doubleValue());
        creditDto.setInterestRate(credit.getInterestRate());
        creditDto.setPeriod(credit.getPeriod().getMonths());
        return creditDto;
    }

    public CreditOfferDto getById(CreditOffer creditOffer) {
        CreditOfferDto creditOfferDto = new CreditOfferDto();
        creditOfferDto.setId(creditOffer.getId().toString());
        creditOfferDto.setClient(getById(creditOffer.getClient()));
        creditOfferDto.setCredit(getById(creditOffer.getCredit()));
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
