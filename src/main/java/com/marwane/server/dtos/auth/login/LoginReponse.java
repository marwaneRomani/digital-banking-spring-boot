package com.marwane.server.dtos.auth.login;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginReponse {
    private String email;
    private String token;
}