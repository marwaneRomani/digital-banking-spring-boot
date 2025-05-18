package com.marwane.server.models;

import com.marwane.server.models.users.Agent;
import com.marwane.server.models.users.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_compte", discriminatorType = DiscriminatorType.STRING)
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Compte {

    @Id
    private String code;

    private double solde;
    private LocalDateTime dateCreation;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;


    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent managedBy;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Operation> operations;
}
