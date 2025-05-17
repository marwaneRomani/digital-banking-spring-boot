package com.marwane.server.service.operation;


import com.marwane.server.dtos.operation.OperationDto;

import java.util.List;

public interface OperationService {
    OperationDto versement(String compteCode, double montant);
    OperationDto retrait(String compteCode, double montant);
    void virement(String compteSource, String compteDestination, double montant);
    List<OperationDto> getHistorique(String compteCode);
}
