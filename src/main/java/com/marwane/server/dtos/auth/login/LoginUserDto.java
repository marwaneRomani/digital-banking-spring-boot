package com.marwane.server.dtos.auth.login;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;

    private String password;
}
