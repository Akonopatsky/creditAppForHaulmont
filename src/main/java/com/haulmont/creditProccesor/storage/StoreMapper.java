package com.haulmont.creditProccesor.storage;

import com.haulmont.creditProccesor.business.model.Client;
import com.haulmont.creditProccesor.storage.Domain.ClientData;
import org.springframework.stereotype.Service;

@Service
public class StoreMapper {
    

    Client convert(ClientData clientData) {
        return new Client(clientData.getId(),
                clientData.getName(),
                clientData.getPhoneNumber(),
                clientData.getPassportNumber());
    }

    ClientData convert(Client client) {
        if (client.getId() == null) {
            return new ClientData(client.getId(),
                    client.getName(),
                    client.getPhoneNumber(),
                    client.getPassportNumber(), null);
        } else {
            ClientData clientData =
        }
    }
    /*
    BankData  convert(Bank bank) {

    }*/
}
