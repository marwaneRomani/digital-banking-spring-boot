package com.marwane.server.dtos;

import com.marwane.server.models.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOperationRequest {
    private String compteCode;
    private double montant;
    private TypeOperation type;
}
