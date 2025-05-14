package com.marwane.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCompteRequest {
    private double soldeInitial;
    private Long clientId;
    private String typeCompte;
    private Double decouvert; // si type = COURANT
    private Double tauxInteret; // si type = EPARGNE
}
