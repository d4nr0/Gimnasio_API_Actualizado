package com.cibertec.gimnasio.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime fecha = LocalDateTime.now();
    private int status;
    private String mensaje;

    public ApiError(int status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
    }
}
