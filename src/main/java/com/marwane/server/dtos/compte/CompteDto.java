package com.marwane.server.dtos.compte;

import com.marwane.server.dtos.client.ClientDto;
import com.marwane.server.models.AccountStatus;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CompteDto {
    private String code;
    private String type;
    private double solde;
    private LocalDateTime dateCreation;
    private AccountStatus status;
    private Long clientId;
    private Long agentId;

    private ClientDto client;
}
