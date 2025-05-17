package com.marwane.server.dtos.operation;

import com.marwane.server.models.TypeOperation;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OperationResponse {
    private Long id;
    private Date dateOperation;
    private double montant;
    private TypeOperation type;
    private String compteCode;
}
