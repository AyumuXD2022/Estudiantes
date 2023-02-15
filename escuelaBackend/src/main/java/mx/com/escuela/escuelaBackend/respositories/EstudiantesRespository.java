package mx.com.escuela.escuelaBackend.respositories;

import mx.com.escuela.escuelaBackend.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudiantesRespository extends JpaRepository<Estudiante,Long> {
}
