package mx.com.escuela.escuelaBackend.services.impl;

import mx.com.escuela.escuelaBackend.models.Estudiante;
import mx.com.escuela.escuelaBackend.respositories.EstudiantesRespository;
import mx.com.escuela.escuelaBackend.services.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstudianteService implements IEstudianteService {
    @Autowired
    private EstudiantesRespository respository;
    @Override
    @Transactional(readOnly = true)
    ///Aqui solo obtiene todo los datos de la base de datos
    public List<Estudiante>listarEstudiantes(){
        return this.respository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    ///Aqui solo obtiene un dato de la base de datos
    public Estudiante encontrarById(Long id){
        return this.respository.findById(id).orElse(null);
    }
    @Override
    @Transactional
    //Aqui vamos a guardar un objeto a la bases de datos
    // Aqui vamos actualizar si el objeto tiene atributos llenos
    public Estudiante guardarEstudiante(Estudiante estudiante){
        return this.respository.save(estudiante);
    }
    @Override
    @Transactional
    //Vamos a eliminar un objeto en la base de datos
    public void eliminar(Long id){
        this.respository.deleteById(id);
    }
}
