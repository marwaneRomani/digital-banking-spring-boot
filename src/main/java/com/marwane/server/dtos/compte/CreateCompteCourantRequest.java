package com.marwane.server.dtos.compte;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCompteCourantRequest {
    @NotBlank(message = "Le code du compte est obligatoire")
    private String code;

    @Min(value = 0, message = "Le solde initial doit être supérieur ou égal à 0")
    private double soldeInitial;

    @NotNull(message = "L'identifiant du client est obligatoire")
    private Long clientId;

    @Min(value = 0, message = "Le découvert doit être supérieur ou égal à 0")
    private double decouvert;
}
