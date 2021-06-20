package com.haulmont.creditProcessor.services.mappers;

import com.haulmont.creditProcessor.model.Credit;
import com.haulmont.creditProcessor.model.MoneyFactory;
import com.haulmont.creditProcessor.web.dto.CreditDto;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditMapper {
    private final MoneyFactory moneyFactory;

    public CreditMapper(MoneyFactory moneyFactory) {
        this.moneyFactory = moneyFactory;
    }

    public Credit getNewCredit(CreditDto creditDto) {
        return new Credit(moneyFactory.getMoneyOf(creditDto.getCreditLimit()),
                creditDto.getInterestRate(),
                Period.ofMonths(creditDto.getPeriod()));
    }

    public CreditDto convertToDto(Credit credit) {
        CreditDto creditDto = new CreditDto();
        creditDto.setId(credit.getId().toString());
        creditDto.setCreditLimit(credit.getCreditLimit().getNumberStripped().doubleValue());
        creditDto.setInterestRate(credit.getInterestRate());
        creditDto.setPeriod(credit.getPeriod().getMonths());
        creditDto.setBankId(credit.getBank().getId().toString());
        return creditDto;
    }

    public List<CreditDto> convertToDtoList(List<Credit> creditList) {
        return creditList.stream().map(this::convertToDto)
                .sorted(Comparator.comparing(CreditDto::getCreditLimit)
                        .thenComparing(CreditDto::getPeriod))
                .collect(Collectors.toList());
    }
}
