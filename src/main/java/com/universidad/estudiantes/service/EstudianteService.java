package com.universidad.estudiantes.service;

import com.universidad.estudiantes.model.Estudiante;
import com.universidad.estudiantes.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Camilo Andres Sanchez Martinez
 */
@Service
public class EstudianteService {
    private final EstudianteRepository repo;
    public EstudianteService(EstudianteRepository repo) {
        this.repo = repo;
    }
    public List<Estudiante> listarTodos() {
        return repo.findAll();
    }
    public Estudiante buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Estudiante no encontrado con ID: " + id));
    }
    @Transactional
    public Estudiante guardar(Estudiante estudiante) {
        // Validación de correo duplicado a nivel de servicio
        if (estudiante.getId() == null) {
            // Nuevo estudiante: verificar que el correo no exista
            if (repo.findByCorreo(estudiante.getCorreo()).isPresent()) {
                throw new IllegalArgumentException(
                        "Ya existe un estudiante con el correo: " + estudiante.getCorreo());
            }
        } else {
            if (repo.existsByCorreoAndIdNot(estudiante.getCorreo(), estudiante.getId())) {
                throw new IllegalArgumentException(
                        "El correo ya está en uso por otro estudiante.");
            }
        }
        return repo.save(estudiante);
    }
    @Transactional
    public void eliminar(Long id) {
        // Verificar que existe antes de eliminar
        buscarPorId(id);
        repo.deleteById(id);
    }
    public List<Estudiante> buscarPorCarrera(String carrera) {
        return repo.findByCarreraIgnoreCase(carrera);
    }
    public List<Estudiante> buscar(String termino) {
        return repo.findByNombreContainingOrApellidoContaining(termino, termino);
    }
}
