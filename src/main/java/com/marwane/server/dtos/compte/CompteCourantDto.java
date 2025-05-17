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
public class CompteCourantDto extends CompteDto {
    private double decouvert;


    @Builder
    public CompteCourantDto(String code, double solde, LocalDateTime dateCreation, AccountStatus status, Long clientId, Long agentId, double decouvert) {
        super(code, solde, dateCreation, status, clientId, agentId);
        this.decouvert = decouvert;
    }
}


