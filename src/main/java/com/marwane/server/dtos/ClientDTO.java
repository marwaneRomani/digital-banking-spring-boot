package com.marwane.server.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
}
