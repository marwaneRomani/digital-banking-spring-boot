package com.marwane.server.service.client;


import com.marwane.server.dtos.client.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto saveClient(ClientDto clientDto);
    ClientDto updateClient(Long id, ClientDto clientDto);
    void deleteClient(Long id);
    ClientDto getClientById(Long id);
    List<ClientDto> getAllClients();
}
