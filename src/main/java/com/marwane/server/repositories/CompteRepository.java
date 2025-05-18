package com.marwane.server.repositories;

import com.marwane.server.models.Compte;
import com.marwane.server.models.users.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {

    Compte findCompteByClientId(Long clientId);

    List<Compte> findCompteByManagedBy(Agent agent);

    @Query("SELECT c FROM Compte c JOIN FETCH c.client LEFT JOIN FETCH c.managedBy")
    List<Compte> findAllWithClientAndAgent();

}
