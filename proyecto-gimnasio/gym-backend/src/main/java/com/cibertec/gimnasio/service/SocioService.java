package com.cibertec.gimnasio.service;

import com.cibertec.gimnasio.dto.SocioDTO;
import com.cibertec.gimnasio.entity.Socio;
import com.cibertec.gimnasio.exception.ResourceNotFoundException;
import com.cibertec.gimnasio.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SocioService {

    private final SocioRepository socioRepository;

    public List<Socio> listarTodos() {
        return socioRepository.findAll();
    }

    public Socio buscarPorId(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado con id: " + id));
    }

    public Socio crear(SocioDTO dto) {
        if (socioRepository.existsByDni(dto.getDni())) {
            throw new IllegalArgumentException("Ya existe un socio con el DNI: " + dto.getDni());
        }

        Socio socio = Socio.builder()
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .dni(dto.getDni())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .fechaNacimiento(dto.getFechaNacimiento())
                .plan(dto.getPlan())
                .fechaInscripcion(dto.getFechaInscripcion() != null ? dto.getFechaInscripcion() : LocalDate.now())
                .activo(dto.getActivo() != null ? dto.getActivo() : true)
                .build();

        return socioRepository.save(socio);
    }

    public Socio actualizar(Long id, SocioDTO dto) {
        Socio socio = buscarPorId(id);

        socio.setNombres(dto.getNombres());
        socio.setApellidos(dto.getApellidos());
        socio.setDni(dto.getDni());
        socio.setEmail(dto.getEmail());
        socio.setTelefono(dto.getTelefono());
        socio.setFechaNacimiento(dto.getFechaNacimiento());
        socio.setPlan(dto.getPlan());
        if (dto.getFechaInscripcion() != null) {
            socio.setFechaInscripcion(dto.getFechaInscripcion());
        }
        if (dto.getActivo() != null) {
            socio.setActivo(dto.getActivo());
        }

        return socioRepository.save(socio);
    }

    public void eliminar(Long id) {
        Socio socio = buscarPorId(id);
        socioRepository.delete(socio);
    }
}
