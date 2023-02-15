package mx.com.escuela.escuelaBackend.services;

import mx.com.escuela.escuelaBackend.models.Estudiante;

import java.util.List;

public interface IEstudianteService {
    List<Estudiante> listarEstudiantes();

    Estudiante encontrarById(Long id);

    Estudiante guardarEstudiante(Estudiante estudiante);

    void eliminar(Long id);
}
