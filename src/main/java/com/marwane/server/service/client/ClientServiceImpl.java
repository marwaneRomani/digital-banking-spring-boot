package com.marwane.server.service.client;

import com.marwane.server.dtos.client.ClientDto;
import com.marwane.server.mappers.client.ClientMapper;
import com.marwane.server.models.users.Agent;
import com.marwane.server.models.users.Client;
import com.marwane.server.repositories.AgentRepository;
import com.marwane.server.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final AgentRepository agentRepository;

    @Override
    public ClientDto saveClient(ClientDto clientDto) {

        String agentEmail = getAuthenticatedUserId();

        Agent agent = agentRepository.findAgentByEmail(agentEmail)
                .orElseThrow(() -> new RuntimeException("Agent non trouvé avec l'id: " + agentEmail));


        Client client = clientMapper.toEntity(clientDto);

        client.setCreatedBy(agent);

        Client savedClient = clientRepository.save(client);

        return clientMapper.toDto(savedClient);
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id: " + id));

        existingClient.setFullName(clientDto.getFullName());
        existingClient.setEmail(clientDto.getEmail());
        existingClient.setTelephone(clientDto.getTelephone());

        Client updatedClient = clientRepository.save(existingClient);
        return clientMapper.toDto(updatedClient);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client non trouvé avec l'id: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id: " + id));
        return clientMapper.toDto(client);
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    private String getAuthenticatedUserId() {
        String authUser = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return authUser;
    }
}
