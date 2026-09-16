package com.cibertec.gimnasio.config;

import com.cibertec.gimnasio.entity.Usuario;
import com.cibertec.gimnasio.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Crea un usuario admin de prueba si no existe ninguno
        // Usuario: admin  /  Password: admin123
        if (!usuarioRepository.existsByUsername("admin")) {
            Usuario admin = Usuario.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .rol("ADMIN")
                    .activo(true)
                    .build();
            usuarioRepository.save(admin);
            System.out.println(">> Usuario admin creado (admin / admin123)");
        }
    }
}
