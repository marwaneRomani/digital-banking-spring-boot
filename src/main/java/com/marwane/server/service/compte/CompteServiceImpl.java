package com.marwane.server.service.compte;

import com.marwane.server.dtos.compte.CompteCourantDto;
import com.marwane.server.dtos.compte.CompteDto;
import com.marwane.server.dtos.compte.CompteEpargneDto;
import com.marwane.server.mappers.compte.CompteMapper;
import com.marwane.server.models.AccountStatus;
import com.marwane.server.models.Compte;
import com.marwane.server.models.CompteCourant;
import com.marwane.server.models.CompteEpargne;
import com.marwane.server.models.users.Agent;
import com.marwane.server.models.users.Client;
import com.marwane.server.repositories.AgentRepository;
import com.marwane.server.repositories.ClientRepository;
import com.marwane.server.repositories.CompteRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;
    private final CompteMapper compteMapper;
    private final AgentRepository agentRepository;


    public CompteServiceImpl(CompteRepository compteRepository, ClientRepository clientRepository, CompteMapper compteMapper, AgentRepository agentRepository) {
        this.compteRepository = compteRepository;
        this.clientRepository = clientRepository;
        this.compteMapper = compteMapper;
        this.agentRepository = agentRepository;
    }

    @Override
    public CompteCourantDto createCompteCourant(CompteCourantDto dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));


        String agentEmail = getAuthenticatedUserId();

        Agent agent = agentRepository.findAgentByEmail(agentEmail)
                .orElseThrow(() -> new RuntimeException("Agent non trouvé avec l'id: " + agentEmail));

        CompteCourant compte = compteMapper.toEntity(dto);
        compte.setCode(UUID.randomUUID().toString());
        compte.setStatus(AccountStatus.CREATED);
        compte.setClient(client);
        compte.setManagedBy(agent);

        CompteCourant saved = compteRepository.save(compte);

        return compteMapper.toCompteCourantDto(saved);
    }

    @Override
    public CompteEpargneDto createCompteEpargne(CompteEpargneDto dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        String agentEmail = getAuthenticatedUserId();

        Agent agent = agentRepository.findAgentByEmail(agentEmail)
                .orElseThrow(() -> new RuntimeException("Agent non trouvé avec l'id: " + agentEmail));


        CompteEpargne compte = compteMapper.toEntity(dto);
        compte.setClient(client);
        compte.setCode(UUID.randomUUID().toString());
        compte.setStatus(AccountStatus.CREATED);
        compte.setManagedBy(agent);

        CompteEpargne saved = compteRepository.save(compte);

        return compteMapper.toCompteEpargneDto(saved);
    }

    public CompteDto changeCompteStatus(AccountStatus status, String code) {
        Compte compte = compteRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        compte.setStatus(status);
        Compte updatedCompte = compteRepository.save(compte);

        return compteMapper.toDto(updatedCompte);
    }

    @Override
    public CompteDto getCompteByCode(String code) {
        Compte compte = compteRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        return compteMapper.toDto(compte);
    }

    @Override
    public List<CompteDto> getComptesByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        return client.getComptes()
                .stream()
                .map(compteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompteDto> getAllComptes() {

        return compteRepository.findAllWithClientAndAgent()
                .stream()
                .map(compteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCompte(String code) {
        if (!compteRepository.existsById(code)) {
            throw new RuntimeException("Compte non trouvé");
        }
        compteRepository.deleteById(code);
    }


    private String getAuthenticatedUserId() {
        String authUser = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return authUser;
    }
}
