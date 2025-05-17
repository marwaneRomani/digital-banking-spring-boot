package com.marwane.server.repositories;

import com.marwane.server.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {

    Compte findCompteByClientId(String clientId);
}
