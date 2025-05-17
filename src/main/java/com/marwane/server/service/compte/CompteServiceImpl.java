package com.marwane.server.service.compte;

import com.marwane.server.dtos.compte.CompteCourantDto;
import com.marwane.server.dtos.compte.CompteDto;
import com.marwane.server.dtos.compte.CompteEpargneDto;
import com.marwane.server.mappers.compte.CompteMapper;
import com.marwane.server.models.Compte;
import com.marwane.server.models.CompteCourant;
import com.marwane.server.models.CompteEpargne;
import com.marwane.server.models.users.Client;
import com.marwane.server.repositories.ClientRepository;
import com.marwane.server.repositories.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;
    private final CompteMapper compteMapper;

    @Override
    public CompteCourantDto createCompteCourant(CompteCourantDto dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        CompteCourant compte = compteMapper.toEntity(dto);
        compte.setClient(client);
        CompteCourant saved = compteRepository.save(compte);

        return compteMapper.toCompteCourantDto(saved);
    }

    @Override
    public CompteEpargneDto createCompteEpargne(CompteEpargneDto dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        CompteEpargne compte = compteMapper.toEntity(dto);
        compte.setClient(client);
        CompteEpargne saved = compteRepository.save(compte);

        return compteMapper.toCompteEpargneDto(saved);
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
        return compteRepository.findAll()
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
