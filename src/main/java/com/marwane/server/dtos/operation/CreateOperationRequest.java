package com.marwane.server.dtos.operation;

import com.marwane.server.models.TypeOperation;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOperationRequest {

    @NotBlank(message = "Account code is required")
    private String compteCode;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private Double montant;

    @NotNull(message = "Operation type is required")
    private TypeOperation type;
}
