package com.haulmont.creditProccesor.services.mappers;

import com.haulmont.creditProccesor.model.Client;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClientMapper {
    public Client getNewClient(ClientDto clientDto) {
        return new Client(clientDto.getName(),
                clientDto.getPhoneNumber(),
                clientDto.getPassportNumber());
    }

    public ClientDto getById(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId().toString());
        clientDto.setName(client.getName());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setPassportNumber(client.getPassportNumber());
        return clientDto;
    }

    public List<ClientDto> getAll(Set<Client> clientSet) {
        return clientSet.stream().map(this::getById).collect(Collectors.toList());
    }

}
