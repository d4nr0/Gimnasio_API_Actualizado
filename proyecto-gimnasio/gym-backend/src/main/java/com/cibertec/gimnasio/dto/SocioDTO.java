package com.cibertec.gimnasio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SocioDTO {

    private Long id;

    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 digitos")
    private String dni;

    @Email(message = "El email no tiene un formato valido")
    private String email;

    private String telefono;

    private LocalDate fechaNacimiento;

    @NotBlank(message = "El plan es obligatorio")
    private String plan;

    private LocalDate fechaInscripcion;

    private Boolean activo;
}
