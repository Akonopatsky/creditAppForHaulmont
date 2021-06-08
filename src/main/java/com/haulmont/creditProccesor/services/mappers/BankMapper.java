package com.haulmont.creditProccesor.services.mappers;

import com.haulmont.creditProccesor.model.Bank;
import com.haulmont.creditProccesor.web.dto.BankDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BankMapper {
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
}
