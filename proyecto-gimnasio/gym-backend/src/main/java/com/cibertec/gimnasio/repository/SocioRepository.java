package com.cibertec.gimnasio.repository;

import com.cibertec.gimnasio.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    Optional<Socio> findByDni(String dni);
    boolean existsByDni(String dni);
}
