package com.haulmont.creditProccesor.services.mappers;

import com.haulmont.creditProccesor.model.Credit;
import com.haulmont.creditProccesor.web.dto.CreditDto;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import java.time.Period;

@Component
public class CreditMapper {

    public Credit getNewCredit(CreditDto creditDto) {
        return new Credit(Money.of(creditDto.getCreditLimit(), "RUB"),
                creditDto.getInterestRate(),
                Period.ofMonths(creditDto.getPeriod()));
    }

    public CreditDto getById(Credit credit) {
        CreditDto creditDto = new CreditDto();
        creditDto.setId(credit.getId().toString());
        creditDto.setCreditLimit(credit.getCreditLimit().getNumberStripped().doubleValue());
        creditDto.setInterestRate(credit.getInterestRate());
        creditDto.setPeriod(credit.getPeriod().getMonths());
        return creditDto;
    }
}
