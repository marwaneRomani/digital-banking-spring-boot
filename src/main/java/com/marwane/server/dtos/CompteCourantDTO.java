package com.marwane.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompteCourantDTO extends CompteDTO {
    private double decouvert;
}
