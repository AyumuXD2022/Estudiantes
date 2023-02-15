package mx.com.escuela.escuelaBackend.respositories;

import mx.com.escuela.escuelaBackend.models.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}
