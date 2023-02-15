package mx.com.escuela.escuelaBackend.controllers;

import jakarta.validation.Valid;
import mx.com.escuela.escuelaBackend.models.Estudiante;
import mx.com.escuela.escuelaBackend.models.Profesor;
import mx.com.escuela.escuelaBackend.services.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProfesorControlador {

    @Autowired
    private IProfesorService profesorService;

    @GetMapping("/profesores")
    public ResponseEntity<List<Profesor>>listarProfesores(){
        return ResponseEntity.ok(this.profesorService.listarProfesor());
    }

    @GetMapping("/profesor/{id}")
    public ResponseEntity<?> profesorById(@PathVariable Long id){
        Profesor profesor = null;
        Map<String,Object> respuesta = new HashMap<>(); // String en el mensaje de existo o errores, Objeto Json la voy a enviar al usuario

        try {
            profesor = this.profesorService.obtenerById(id);
        }catch (DataAccessException e){
            respuesta.put("mensaje","Error al realizar la consulta en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (profesor == null){
            respuesta.put("mensaje","El id de profesor " + id + " no se encuentra en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(profesor,HttpStatus.OK);
    }

    @PostMapping("/profesor")
    public ResponseEntity<?> guardarProfesor(@Valid @RequestBody Profesor profesor, BindingResult result){
        Profesor profesorNew = null;
        Map<String,Object> respuesta = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            respuesta.put("errors", errors);
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
        }
        try {
            profesorNew = this.profesorService.guardarProfesor(profesor);
        }catch (DataAccessException e){
            respuesta.put("mensaje","Error al realizar un insert en la bases de datos");
        }
        respuesta.put("mensaje","El profesor se ha creado de la manera coorectas");
        respuesta.put("profesor",profesorNew);

        return new ResponseEntity<>(respuesta,HttpStatus.CREATED);
    }


    @PutMapping("/profesor/{id}")
    public ResponseEntity<?> actualizarProfesor(@Valid @RequestBody Profesor profesor, BindingResult result, @PathVariable Long id){
        Profesor estudianteActual = this.profesorService.obtenerById(id);
        Profesor profesorUpdate = null;
        Map<String,Object> respuesta = new HashMap<>();

        if(estudianteActual == null){
            respuesta.put("mensaje","El id de profesor " + id + " no se encuentra en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            respuesta.put("errors", errors);
            return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
        }

        try {
            estudianteActual.setNombre(profesor.getNombre());
            estudianteActual.setApellido(profesor.getApellido());
            estudianteActual.setEmail(profesor.getEmail());
            estudianteActual.setDireccion(profesor.getDireccion());
            estudianteActual.setTelefono(profesor.getTelefono());

            profesorUpdate = this.profesorService.guardarProfesor(estudianteActual);
        }catch (DataAccessException e){
            respuesta.put("mensaje","Error al realizar un update en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        respuesta.put("mensaje","El profesor se ha actualizado de la manera correcta");
        respuesta.put("profesor",profesorUpdate);

        return new ResponseEntity<>(respuesta,HttpStatus.CREATED);

    }

    @DeleteMapping("/profesor/{id}")
    public ResponseEntity<?>elimiarProfesor(@PathVariable Long id){
        Map<String,Object> respuesta = new HashMap<>();
        try {
            this.profesorService.eliminarProfesor(id);
        }catch (DataAccessException e){
            respuesta.put("mensaje","Error al realizar un delete en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje","El profesor se ha eliminado de la manera correcta");

        return new ResponseEntity<>(respuesta,HttpStatus.CREATED);
    }
}
