package mx.com.escuela.escuelaBackend.controllers;

import jakarta.validation.Valid;
import mx.com.escuela.escuelaBackend.models.Clase;
import mx.com.escuela.escuelaBackend.models.Profesor;
import mx.com.escuela.escuelaBackend.services.IClaseService;
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
public class ClaseControlador {

    private final IClaseService service;

    public ClaseControlador(IClaseService service) {
        this.service = service;
    }

    @GetMapping("/clases")
    public ResponseEntity<List<Clase>> listarClases(){
        return ResponseEntity.ok(this.service.listarClase());
    }

    @GetMapping("/clase/{id}")
    public ResponseEntity<?> claseById(@PathVariable Long id){
        Clase clase = null;
        Map<String,Object> respuesta = new HashMap<>(); // String en el mensaje de existo o errores, Objeto Json la voy a enviar al usuario

        try {
            clase = this.service.claseById(id);
        }catch (DataAccessException e){
            respuesta.put("mensaje","Error al realizar la consulta en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (clase == null){
            respuesta.put("mensaje","El id de clase " + id + " no se encuentra en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(clase,HttpStatus.OK);
    }

    @PostMapping("/clase")
    public ResponseEntity<?> guardarClase(@Valid @RequestBody Clase clase, BindingResult result){
        Clase claseNew = null;
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
            claseNew = this.service.guardarClase(clase);
        }catch (DataAccessException e){
            respuesta.put("mensaje","Error al realizar un insert en la bases de datos");
        }
        respuesta.put("mensaje","El clase se ha creado de la manera coorectas");
        respuesta.put("clase",claseNew);

        return new ResponseEntity<>(respuesta,HttpStatus.CREATED);
    }


    @PutMapping("/clase/{id}")
    public ResponseEntity<?> actualizarProfesor(@Valid @RequestBody Clase clase, BindingResult result, @PathVariable Long id){
        Clase claseActual = this.service.claseById(id);
        Clase claseUpdate = null;
        Map<String,Object> respuesta = new HashMap<>();

        if(claseActual == null){
            respuesta.put("mensaje","El id de clase " + id + " no se encuentra en la bases de datos");
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
            claseActual.setNombre(clase.getNombre());
            claseActual.setDescripcion(clase.getDescripcion());
            claseActual.setFechaFin(clase.getFechaFin());
            claseActual.setFechaInicio(clase.getFechaInicio());

            claseUpdate = this.service.guardarClase(claseActual);
        }catch (DataAccessException e){
            respuesta.put("mensaje","Error al realizar un update en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        respuesta.put("mensaje","El clase se ha actualizado de la manera correcta");
        respuesta.put("clase",claseUpdate);

        return new ResponseEntity<>(respuesta,HttpStatus.CREATED);

    }


    @DeleteMapping("/clase/{id}")
    public ResponseEntity<?>elimiarClase(@PathVariable Long id){
        Map<String,Object> respuesta = new HashMap<>();
        try {
            this.service.eliminarClase(id);
        }catch (DataAccessException e){
            respuesta.put("mensaje","Error al realizar un delete en la bases de datos");
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje","La clase se ha eliminado de la manera correcta");

        return new ResponseEntity<>(respuesta,HttpStatus.CREATED);
    }


}
