package mx.com.escuela.escuelaBackend.services;

import mx.com.escuela.escuelaBackend.models.Profesor;

import java.util.List;

public interface IProfesorService {
    List<Profesor> listarProfesor();

    Profesor obtenerById(Long id);

    Profesor guardarProfesor(Profesor profesor);

    void eliminarProfesor(Long id);
}
