package com.marwane.server.dtos;

import com.marwane.server.models.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationDTO {
    private Long id;
    private Date dateOperation;
    private double montant;
    private TypeOperation type;
    private String compteCode;
}
