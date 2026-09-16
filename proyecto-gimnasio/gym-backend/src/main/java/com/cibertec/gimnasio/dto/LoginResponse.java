package com.cibertec.gimnasio.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String tipo = "Bearer";
    private String username;
    private String rol;

    public LoginResponse(String token, String username, String rol) {
        this.token = token;
        this.username = username;
        this.rol = rol;
    }
}
