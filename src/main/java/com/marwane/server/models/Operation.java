package com.marwane.server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOperation;
    private double montant;

    @Enumerated(EnumType.STRING)
    private TypeOperation type;

    @ManyToOne
    @JoinColumn(name = "compte_id")
    private Compte compte;
}
