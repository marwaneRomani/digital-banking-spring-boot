package com.marwane.server.service.compte;


import com.marwane.server.dtos.compte.CompteCourantDto;
import com.marwane.server.dtos.compte.CompteDto;
import com.marwane.server.dtos.compte.CompteEpargneDto;
import com.marwane.server.models.AccountStatus;

import java.util.List;

public interface CompteService {
    CompteCourantDto createCompteCourant(CompteCourantDto dto);
    CompteEpargneDto createCompteEpargne(CompteEpargneDto dto);
    CompteDto getCompteByCode(String code);

    CompteDto changeCompteStatus(AccountStatus status, String code);


    List<CompteDto> getComptesByClient(Long clientId);
    List<CompteDto> getAllComptes();
    void deleteCompte(String code);
}
