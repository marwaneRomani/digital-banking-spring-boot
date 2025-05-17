package com.marwane.server.mappers.client;

import com.marwane.server.dtos.client.ClientDto;
import com.marwane.server.models.users.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto toDto(Client client) {
        if (client == null) return null;

        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setFullName(client.getFullName());
        dto.setEmail(client.getEmail());
        dto.setTelephone(client.getTelephone());
        return dto;

    }

    public Client toEntity(ClientDto dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setId(dto.getId());
        client.setFullName(dto.getFullName());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());

        return client;
    }
}
