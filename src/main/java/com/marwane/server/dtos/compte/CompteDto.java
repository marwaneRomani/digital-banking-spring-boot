package com.marwane.server.dtos.compte;

import com.marwane.server.models.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompteDto {
    private String code;
    private double solde;
    private LocalDateTime dateCreation;
    private AccountStatus status;
    private Long clientId;
    private Long agentId;
}
