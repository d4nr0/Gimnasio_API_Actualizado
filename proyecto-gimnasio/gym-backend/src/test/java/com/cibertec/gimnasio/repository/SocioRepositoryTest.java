package com.cibertec.gimnasio.repository;

import com.cibertec.gimnasio.entity.Socio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SocioRepositoryTest {

    @Autowired
    private SocioRepository socioRepository;

    private Socio crearSocioDePrueba(String dni) {
        return Socio.builder()
                .nombres("Juan")
                .apellidos("Perez")
                .dni(dni)
                .email("juan.perez@correo.com")
                .telefono("999999999")
                .fechaNacimiento(LocalDate.of(2000, 1, 1))
                .plan("MENSUAL")
                .fechaInscripcion(LocalDate.now())
                .activo(true)
                .build();
    }

    @Test
    @DisplayName("Debe insertar un socio correctamente")
    void testInsertar() {
        Socio socio = crearSocioDePrueba("12345678");

        Socio guardado = socioRepository.save(socio);

        assertNotNull(guardado.getId());
        assertEquals("Juan", guardado.getNombres());
        assertEquals("12345678", guardado.getDni());
    }

    @Test
    @DisplayName("Debe listar todos los socios registrados")
    void testListar() {
        socioRepository.save(crearSocioDePrueba("11111111"));
        socioRepository.save(crearSocioDePrueba("22222222"));

        List<Socio> socios = socioRepository.findAll();

        assertEquals(2, socios.size());
    }

    @Test
    @DisplayName("Debe actualizar los datos de un socio existente")
    void testActualizar() {
        Socio socio = socioRepository.save(crearSocioDePrueba("33333333"));

        socio.setNombres("Carlos");
        socio.setPlan("ANUAL");
        Socio actualizado = socioRepository.save(socio);

        assertEquals("Carlos", actualizado.getNombres());
        assertEquals("ANUAL", actualizado.getPlan());
    }

    @Test
    @DisplayName("Debe eliminar un socio existente")
    void testEliminar() {
        Socio socio = socioRepository.save(crearSocioDePrueba("44444444"));
        Long id = socio.getId();

        socioRepository.deleteById(id);
        Optional<Socio> resultado = socioRepository.findById(id);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Debe encontrar un socio por su DNI")
    void testBuscarPorDni() {
        socioRepository.save(crearSocioDePrueba("55555555"));

        Optional<Socio> resultado = socioRepository.findByDni("55555555");

        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getNombres());
    }
}
