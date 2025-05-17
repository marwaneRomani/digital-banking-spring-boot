package com.marwane.server.dtos.operation;

import com.marwane.server.models.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationDto {
    private Long id;
    private LocalDateTime dateOperation;
    private double montant;
    private TypeOperation type;
    private String compteCode;
}
