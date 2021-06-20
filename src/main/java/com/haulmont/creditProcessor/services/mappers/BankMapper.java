package com.haulmont.creditProcessor.services.mappers;

import com.haulmont.creditProcessor.model.Bank;
import com.haulmont.creditProcessor.web.dto.BankDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankMapper {
    public Bank getNewBank(BankDto bankDto) {
        return new Bank(bankDto.getName());
    }

    public List<BankDto> convertToDtoList(List<Bank> bankList) {
        return bankList.stream().map(bank -> convertToDto(bank))
                .sorted(Comparator.comparing(BankDto::getName))
                .collect(Collectors.toList());
    }

    public BankDto convertToDto(Bank bank) {
        BankDto bankDto = new BankDto();
        bankDto.setId(bank.getId().toString());
        bankDto.setName(bank.getName());
        return bankDto;
    }
}
