package com.marwane.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompteDTO {
    private String code;
    private double solde;
    private Date dateCreation;
    private Long clientId;
}
