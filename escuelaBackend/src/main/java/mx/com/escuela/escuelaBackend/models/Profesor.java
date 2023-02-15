package mx.com.escuela.escuelaBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Table(name = "profesores")
@Data
@Entity
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false,length = 40)
    @Pattern(regexp = "[a-zA-ZÀ-ÖØ-öø-ÿ]+\\.?(( |\\-)[a-zA-ZÀ-ÖØ-öø-ÿ]+\\.?)*", message = "Escriba bien su nombre")
    private String nombre;

    @Column(nullable = false,length = 40)
    @Pattern(regexp = "[a-zA-ZÀ-ÖØ-öø-ÿ]+\\.?(( |\\-)[a-zA-ZÀ-ÖØ-öø-ÿ]+\\.?)*", message = "Escriba bien su apellido")
    private String apellido;

    @Column(nullable = false,length = 20)
    @Pattern(regexp = "0{0,2}([\\+]?[\\d]{1,3} ?)?([\\(]([\\d]{2,3})[)] ?)?[0-9][0-9 \\-]{6,}( ?([xX]|([eE]xt[\\.]?)) ?([\\d]{1,5}))?",message = "Escriba su numero de la manera correcta")
    private String telefono;

    @Column(nullable = false,length = 100)
    @Pattern(regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@”]+(\\.[^<>()\\[\\]\\\\.,;:\\s@”]+)*)|(“.+”))@((\\[[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}])|(([a-zA-Z\\-0–9]+\\.)+[a-zA-Z]{2,}))$" ,message = "Escriba su correo de la manera correcta")
    private String email;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "direccion_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Direccion direccion;
}
