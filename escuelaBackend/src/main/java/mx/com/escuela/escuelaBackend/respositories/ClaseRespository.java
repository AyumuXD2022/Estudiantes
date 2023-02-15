package mx.com.escuela.escuelaBackend.respositories;

import mx.com.escuela.escuelaBackend.models.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaseRespository extends JpaRepository<Clase,Long> {
}
