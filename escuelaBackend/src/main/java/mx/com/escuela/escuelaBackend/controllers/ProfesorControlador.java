package mx.com.escuela.escuelaBackend.controllers;

import mx.com.escuela.escuelaBackend.models.Profesor;
import mx.com.escuela.escuelaBackend.services.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfesorControlador {

    @Autowired
    private IProfesorService profesorService;

    @GetMapping("/profesores")
    public List<Profesor>listarProfesores(){
        return this.profesorService.listarProfesor();
    }

    @GetMapping("/profesor/{id}")
    public Profesor profesorById(@PathVariable Long id){
        return this.profesorService.obtenerById(id);
    }
}
