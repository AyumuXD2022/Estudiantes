package mx.com.escuela.escuelaBackend.services;

import mx.com.escuela.escuelaBackend.models.Clase;

import java.util.List;

public interface IClaseService {
    List<Clase> listarClase();

    Clase claseById(Long id);

    Clase guardarClase(Clase clase);

    void eliminarClase(Long id);
}
