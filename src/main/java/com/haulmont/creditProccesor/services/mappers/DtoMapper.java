package com.haulmont.creditProccesor.services.mappers;

import com.haulmont.creditProccesor.storage.Domain.BankData;
import com.haulmont.creditProccesor.web.dto.*;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DtoMapper {
    public BankData saveForm(BankDto bankDto) {
        return new BankData(bankDto.getName());
    }

    public BankDto findById(BankData bankdata) {
        BankDto bankDto = new BankDto();
        bankDto.setId(bankdata.getId().toString());
        bankDto.setName(bankdata.getName());
        return bankDto;
    }

    public Set<BankDto> findAll(Set<BankData> bankDataSet) {
        return bankDataSet.stream().map(bankEntity -> findById(bankEntity)).collect(Collectors.toSet());
    }


}
