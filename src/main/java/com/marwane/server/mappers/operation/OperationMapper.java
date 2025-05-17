package com.marwane.server.mappers.operation;

import com.marwane.server.dtos.operation.OperationDto;
import com.marwane.server.models.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper {
    public OperationDto toDto(Operation operation) {
        OperationDto dto = new OperationDto();
        dto.setId(operation.getId());
        dto.setDateOperation(operation.getDateOperation());
        dto.setMontant(operation.getMontant());
        dto.setType(operation.getType());
        dto.setCompteCode(operation.getCompte().getCode());
        return dto;
    }

    public Operation toEntity(OperationDto dto) {
        Operation operation = new Operation();
        operation.setId(dto.getId());
        operation.setDateOperation(dto.getDateOperation());
        operation.setMontant(dto.getMontant());
        operation.setType(dto.getType());

        return operation;
    }
}
