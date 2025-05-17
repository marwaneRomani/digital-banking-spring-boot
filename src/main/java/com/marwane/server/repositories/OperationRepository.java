package com.marwane.server.repositories;

import com.marwane.server.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByCompteCode(String compteCode);
}
