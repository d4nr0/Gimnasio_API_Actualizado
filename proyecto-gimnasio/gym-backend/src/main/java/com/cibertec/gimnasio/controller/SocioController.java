package com.cibertec.gimnasio.controller;

import com.cibertec.gimnasio.dto.SocioDTO;
import com.cibertec.gimnasio.entity.Socio;
import com.cibertec.gimnasio.service.SocioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
@RequiredArgsConstructor
public class SocioController {

    private final SocioService socioService;

    // GET /api/socios -> lista todos los socios
    @GetMapping
    public ResponseEntity<List<Socio>> listar() {
        return ResponseEntity.ok(socioService.listarTodos());
    }

    // GET /api/socios/{id} -> obtiene un socio por id
    @GetMapping("/{id}")
    public ResponseEntity<Socio> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(socioService.buscarPorId(id));
    }

    // POST /api/socios -> crea un nuevo socio
    @PostMapping
    public ResponseEntity<Socio> crear(@Valid @RequestBody SocioDTO dto) {
        Socio nuevo = socioService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // PUT /api/socios/{id} -> actualiza un socio existente
    @PutMapping("/{id}")
    public ResponseEntity<Socio> actualizar(@PathVariable Long id, @Valid @RequestBody SocioDTO dto) {
        Socio actualizado = socioService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE /api/socios/{id} -> elimina un socio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        socioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
