package com.universidad.estudiantes.repository;

import com.universidad.estudiantes.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Camilo Andres Sanchez Martinez
 */
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findByCarreraIgnoreCase(String carrera);
    List<Estudiante> findByNombreContainingOrApellidoContaining(
            String nombre, String apellido);
    Optional<Estudiante> findByCorreo(String correo);
    boolean existsByCorreoAndIdNot(String correo, Long id);
}
