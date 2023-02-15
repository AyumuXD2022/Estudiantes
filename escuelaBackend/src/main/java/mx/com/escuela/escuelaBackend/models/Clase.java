package mx.com.escuela.escuelaBackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "clases")
@Data
@Entity
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long id;

    @Column( nullable = false,length = 30)
    private String nombre;

    @Column( nullable = false)
    private String descripcion;

    @Column(name = "fecha_inicio",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

}
