package mx.com.escuela.escuelaBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "matriculas")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id",nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clase clase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Profesor profesor;
    @Column(nullable = false,name = "fecha_matricula")
    private Date fechaMatricula;
}
