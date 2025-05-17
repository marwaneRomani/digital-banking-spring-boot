package com.marwane.server.dtos.compte;

import com.marwane.server.models.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompteEpargneDto extends CompteDto {
    private double tauxInteret;

    @Builder
    public CompteEpargneDto(String code, double solde, LocalDateTime dateCreation, AccountStatus status, Long clientId, Long agentId, double tauxInteret) {
        super(code, solde, dateCreation, status, clientId, agentId);
        this.tauxInteret = tauxInteret;
    }
}