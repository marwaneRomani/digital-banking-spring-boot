package com.marwane.server.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@DiscriminatorValue("COURANT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompteCourant extends Compte implements Serializable {
    private double decouvert;
}
