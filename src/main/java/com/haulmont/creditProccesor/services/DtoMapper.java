package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.dao.Entities.BankEntity;
import com.haulmont.creditProccesor.web.dto.BankDto;
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

    public Set<BankDto> findAll(Set<BankEntity> bankEntitySet) {
        return bankEntitySet.stream().map(bankEntity -> findById(bankEntity)).collect(Collectors.toSet());
    }

}
