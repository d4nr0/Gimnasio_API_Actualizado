package com.cibertec.gimnasio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    // Password cifrado con BCryptPasswordEncoder, nunca se guarda en texto plano
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    @Builder.Default
    private String rol = "ADMIN";

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}
