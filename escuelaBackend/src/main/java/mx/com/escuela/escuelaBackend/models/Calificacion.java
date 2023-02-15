package mx.com.escuela.escuelaBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "calificaciones")
@Data
@Entity
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long id;
    @Column( nullable = false)
    private Double calificacion;

    @Column( nullable = false, name = "fecha_evaluacion")
    private Date fechaEvaluacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clases_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Clase clases;
}
