package mx.com.escuela.escuelaBackend.controllers;

import jakarta.validation.Valid;
import mx.com.escuela.escuelaBackend.models.Estudiante;
import mx.com.escuela.escuelaBackend.services.IEstudianteService;
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
public class AlumnoControlador {
    @Autowired
    private IEstudianteService estudianteService;

    @GetMapping("/estudiantes") //Plural
    public ResponseEntity<List<Estudiante>> listarEstudiantes(){
        return ResponseEntity.ok(this.estudianteService.listarEstudiantes());
    }
    @GetMapping("/estudiante/{id}") //Singular
    public ResponseEntity<?> buscarEstudiante(@PathVariable Long id){
        Estudiante estudiante = null;
        Map<String,Object> respuesta = new HashMap<>(); // String en el mensaje de existo o errores, Objeto Json la voy a enviar al usuario

        try {
            estudiante = this.estudianteService.encontrarById(id);
        }catch (DataAccessException e){
            respuesta.put("mensaje","Error al realizar la consulta en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (estudiante == null){
            respuesta.put("mensaje","El id de estudiante " + id + " no se encuentra en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(estudiante,HttpStatus.OK);

    }

    @PostMapping("/estudiante")
    public ResponseEntity<?> guardarEstudiantes(@Valid @RequestBody Estudiante estudiante, BindingResult result){
        Estudiante estudianteNew = null;
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
            estudianteNew = this.estudianteService.guardarEstudiante(estudiante);
        }catch (DataAccessException e){
            respuesta.put("mensaje","Error al realizar un insert en la bases de datos");
        }
        respuesta.put("mensaje","El estudiante se ha creado de la manera coorectas");
        respuesta.put("estudiantes",estudianteNew);

        return new ResponseEntity<>(respuesta,HttpStatus.CREATED);
    }

    @PutMapping("/estudiante/{id}")
    public ResponseEntity<?> actualizarEstudiante(@Valid @RequestBody Estudiante estudiante, BindingResult result, @PathVariable Long id){
        Estudiante estudianteActual = this.estudianteService.encontrarById(id);
        Estudiante estudianteUpdate = null;
        Map<String,Object> respuesta = new HashMap<>();

        if(estudianteActual == null){
            respuesta.put("mensaje","El id de estudiante " + id + " no se encuentra en la bases de datos");
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
            estudianteActual.setNombre(estudiante.getNombre());
            estudianteActual.setApellido(estudiante.getApellido());
            estudianteActual.setEmail(estudiante.getEmail());
            estudianteActual.setDireccion(estudiante.getDireccion());
            estudianteActual.setTelefono(estudiante.getTelefono());

            estudianteUpdate = this.estudianteService.guardarEstudiante(estudianteActual);
        }catch (DataAccessException e){
            respuesta.put("mensaje","Error al realizar un update en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        respuesta.put("mensaje","El estudiante se ha actualizado de la manera correcta");
        respuesta.put("estudiantes",estudianteUpdate);

        return new ResponseEntity<>(respuesta,HttpStatus.CREATED);

    }

    @DeleteMapping("/estudiante/{id}")
    public ResponseEntity<?>elimiarEstudiantes(@PathVariable Long id){
        Map<String,Object> respuesta = new HashMap<>();
        try {
            this.estudianteService.eliminar(id);
        }catch (DataAccessException e){
            respuesta.put("mensaje","Error al realizar un delete en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje","El estudiante se ha eliminado de la manera correcta");

        return new ResponseEntity<>(respuesta,HttpStatus.CREATED);
    }
}
