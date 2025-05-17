package com.marwane.server.service.operation;

import com.marwane.server.dtos.operation.OperationDto;
import com.marwane.server.exceptions.operation.CompteNotFoundException;
import com.marwane.server.exceptions.operation.SoldeInsuffisantException;
import com.marwane.server.mappers.operation.OperationMapper;
import com.marwane.server.models.Compte;
import com.marwane.server.models.Operation;
import com.marwane.server.models.TypeOperation;
import com.marwane.server.repositories.CompteRepository;
import com.marwane.server.repositories.OperationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final CompteRepository compteRepository;
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    @Override
    public OperationDto versement(String compteCode, double montant) {
        Compte compte = compteRepository.findById(compteCode)
                .orElseThrow(() -> new CompteNotFoundException("Compte introuvable : " + compteCode));

        Operation operation = new Operation();
        operation.setType(TypeOperation.CREDIT);
        operation.setDateOperation(LocalDateTime.now());
        operation.setMontant(montant);
        operation.setCompte(compte);

        compte.setSolde(compte.getSolde() + montant);
        compteRepository.save(compte);

        Operation savedOp = operationRepository.save(operation);
        return operationMapper.toDto(savedOp);
    }

    @Override
    public OperationDto retrait(String compteCode, double montant) {
        Compte compte = compteRepository.findById(compteCode)
                .orElseThrow(() -> new CompteNotFoundException("Compte introuvable : " + compteCode));

        double soldeDisponible = compte.getSolde();
        if (compte instanceof com.marwane.server.models.CompteCourant courant) {
            soldeDisponible += courant.getDecouvert();
        }

        if (soldeDisponible < montant) {
            throw new SoldeInsuffisantException("Solde insuffisant pour le retrait.");
        }

        Operation operation = new Operation();
        operation.setType(TypeOperation.DEBIT);
        operation.setDateOperation(LocalDateTime.now());
        operation.setMontant(montant);
        operation.setCompte(compte);

        compte.setSolde(compte.getSolde() - montant);
        compteRepository.save(compte);

        Operation savedOp = operationRepository.save(operation);
        return operationMapper.toDto(savedOp);
    }

    @Override
    public void virement(String compteSource, String compteDestination, double montant) {
        retrait(compteSource, montant);
        versement(compteDestination, montant);
    }

    @Override
    public List<OperationDto> getHistorique(String compteCode) {
        Compte compte = compteRepository.findById(compteCode)
                .orElseThrow(() -> new CompteNotFoundException("Compte introuvable : " + compteCode));

        List<Operation> operations = operationRepository.findByCompteCode(compteCode);
        return operations.stream().map(operationMapper::toDto).collect(Collectors.toList());
    }
}
