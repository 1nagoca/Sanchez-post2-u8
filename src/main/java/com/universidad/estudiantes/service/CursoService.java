package com.universidad.estudiantes.service;

import com.universidad.estudiantes.model.Curso;
import com.universidad.estudiantes.model.Estudiante;
import com.universidad.estudiantes.repository.CursoRepository;
import com.universidad.estudiantes.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 *
 * @author Camilo Andres Sanchez Martinez
 */
@Service
public class CursoService {

    private final CursoRepository cursoRepo;
    private final EstudianteRepository estudianteRepo;

    public CursoService(CursoRepository cursoRepo, EstudianteRepository estudianteRepo) {
        this.cursoRepo = cursoRepo;
        this.estudianteRepo = estudianteRepo;
    }

    public List<Curso> listarTodos() {
        return cursoRepo.findAllConEstudiantes();
    }

    public Curso buscarPorId(Long id) {
        return cursoRepo.findByIdConEstudiantes(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado: " + id));
    }

    @Transactional
    public Curso guardar(Curso curso) {
        return cursoRepo.save(curso);
    }

    @Transactional
    public void eliminar(Long id) {
        Curso curso = buscarPorId(id);
        // Limpiar relación bidireccional antes de eliminar
        for (Estudiante e : curso.getEstudiantes()) {
            e.getCursos().remove(curso);
        }
        curso.getEstudiantes().clear();
        cursoRepo.delete(curso);
    }

    @Transactional
    public void inscribirEstudiante(Long cursoId, Long estudianteId) {
        Curso curso = buscarPorId(cursoId);
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + estudianteId));
        if (curso.getEstudiantes().contains(estudiante)) {
            throw new IllegalStateException("El estudiante ya está inscrito en este curso.");
        }
        curso.agregarEstudiante(estudiante);
        cursoRepo.save(curso);
    }

    @Transactional
    public void desinscribirEstudiante(Long cursoId, Long estudianteId) {
        Curso curso = buscarPorId(cursoId);
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + estudianteId));

        curso.quitarEstudiante(estudiante);
        cursoRepo.save(curso);
    }
}
