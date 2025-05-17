package com.marwane.server.mappers.compte;
import com.marwane.server.dtos.compte.CompteCourantDto;
import com.marwane.server.dtos.compte.CompteDto;
import com.marwane.server.dtos.compte.CompteEpargneDto;
import com.marwane.server.models.Compte;
import com.marwane.server.models.CompteCourant;
import com.marwane.server.models.CompteEpargne;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    public CompteDto toDto(Compte compte) {
        if (compte instanceof CompteCourant courant) {
            return toCompteCourantDto(courant);
        } else if (compte instanceof CompteEpargne epargne) {
            return toCompteEpargneDto(epargne);
        }
        return null;
    }

    public CompteCourantDto toCompteCourantDto(CompteCourant compte) {

        return CompteCourantDto.builder()
                .code(compte.getCode())
                .solde(compte.getSolde())
                .dateCreation(compte.getDateCreation())
                .status(compte.getStatus())
                .clientId(compte.getClient().getId())
                .decouvert(compte.getDecouvert())
                .build();
    }

    public CompteEpargneDto toCompteEpargneDto(CompteEpargne compte) {
        return CompteEpargneDto.builder()
                .code(compte.getCode())
                .solde(compte.getSolde())
                .dateCreation(compte.getDateCreation())
                .status(compte.getStatus())
                .clientId(compte.getClient().getId())
                .tauxInteret(compte.getTauxInteret())
                .build();
    }

    public CompteCourant toEntity(CompteCourantDto dto) {
        CompteCourant compte = new CompteCourant();
        compte.setCode(dto.getCode());
        compte.setSolde(dto.getSolde());
        compte.setDateCreation(dto.getDateCreation());
        compte.setStatus(dto.getStatus());
        compte.setDecouvert(dto.getDecouvert());
        return compte;
    }

    public CompteEpargne toEntity(CompteEpargneDto dto) {
        CompteEpargne compte = new CompteEpargne();
        compte.setCode(dto.getCode());
        compte.setSolde(dto.getSolde());
        compte.setDateCreation(dto.getDateCreation());
        compte.setStatus(dto.getStatus());
        compte.setTauxInteret(dto.getTauxInteret());
        return compte;
    }
}
