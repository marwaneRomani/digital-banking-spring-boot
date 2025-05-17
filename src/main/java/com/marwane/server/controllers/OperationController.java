package com.marwane.server.controllers;

import com.marwane.server.dtos.operation.OperationDto;
import com.marwane.server.service.operation.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @PostMapping("/versement")
    public OperationDto versement(@RequestParam String compteCode, @RequestParam double montant) {
        return operationService.versement(compteCode, montant);
    }

    @PostMapping("/retrait")
    public OperationDto retrait(@RequestParam String compteCode, @RequestParam double montant) {
        return operationService.retrait(compteCode, montant);
    }

    @PostMapping("/virement")
    public void virement(
            @RequestParam String compteSource,
            @RequestParam String compteDestination,
            @RequestParam double montant
    ) {
        operationService.virement(compteSource, compteDestination, montant);
    }

    @GetMapping("/historique/{compteCode}")
    public List<OperationDto> getHistorique(@PathVariable String compteCode) {
        return operationService.getHistorique(compteCode);
    }
}
