package com.marwane.server.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@DiscriminatorValue("EPARGNE")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompteEpargne extends Compte implements Serializable {
    private double tauxInteret;
}
