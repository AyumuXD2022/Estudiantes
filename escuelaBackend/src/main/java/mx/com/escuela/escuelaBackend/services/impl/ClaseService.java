package mx.com.escuela.escuelaBackend.services.impl;

import mx.com.escuela.escuelaBackend.models.Clase;
import mx.com.escuela.escuelaBackend.respositories.ClaseRespository;
import mx.com.escuela.escuelaBackend.services.IClaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClaseService implements IClaseService {

    private final ClaseRespository respository;

    public ClaseService(ClaseRespository respository) {
        this.respository = respository;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Clase>listarClase(){
        return this.respository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Clase claseById(Long id){
        return this.respository.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public Clase guardarClase(Clase clase){
        return this.respository.save(clase);
    }
    @Override
    @Transactional
    public void eliminarClase(Long id){
        this.respository.deleteById(id);
    }
}
