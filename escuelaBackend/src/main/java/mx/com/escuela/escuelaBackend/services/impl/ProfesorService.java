package mx.com.escuela.escuelaBackend.services.impl;

import mx.com.escuela.escuelaBackend.models.Profesor;
import mx.com.escuela.escuelaBackend.respositories.ProfesorRepository;
import mx.com.escuela.escuelaBackend.services.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfesorService implements IProfesorService {

    @Autowired
    private ProfesorRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Profesor>listarProfesor(){
        return this.repository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Profesor obtenerById(Long id){
        return this.repository.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public Profesor guardarProfesor(Profesor profesor){
        return this.repository.save(profesor);
    }
    @Override
    @Transactional
    public void eliminarProfesor(Long id){
        this.repository.deleteById(id);
    }
}