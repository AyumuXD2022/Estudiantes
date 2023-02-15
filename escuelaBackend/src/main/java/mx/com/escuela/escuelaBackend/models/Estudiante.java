package mx.com.escuela.escuelaBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Table(name = "estudiantes") //Donde le indicamos donde ira en la tablas JPA
@Data
@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id; //"id": 2,

    @Column(nullable = false,length = 40)///Hibernate
    //Validadores regulares
    @Pattern(regexp = "[a-zA-ZÀ-ÖØ-öø-ÿ]+\\.?(( |\\-)[a-zA-ZÀ-ÖØ-öø-ÿ]+\\.?)*", message = "Escriba bien su nombre")
    @NotEmpty(message = "No puede estar vacio")
    private String nombre; //"nombre": "Santiago" JPA Persiten los datos,

    @Column(nullable = false,length = 40)///Hibernate
    //Validadores regulares
    @Pattern(regexp = "[a-zA-ZÀ-ÖØ-öø-ÿ]+\\.?(( |\\-)[a-zA-ZÀ-ÖØ-öø-ÿ]+\\.?)*", message = "Escriba bien su nombre")
    @NotEmpty(message = "No puede estar vacio")
    private String apellido; //"apellido": "Fulano" JPA Persiten los datos,

    @Column(nullable = false,length = 20) ///Hibernate
    //Validadores regulares
    @Pattern(regexp = "0{0,2}([\\+]?[\\d]{1,3} ?)?([\\(]([\\d]{2,3})[)] ?)?[0-9][0-9 \\-]{6,}( ?([xX]|([eE]xt[\\.]?)) ?([\\d]{1,5}))?",message = "Escriba su numero de la manera correcta")
    @NotEmpty(message = "No puede estar vacio")
    private String telefono; //"telefono": "55854562" JPA Persiten los datos,
/*

 */
    @Column(nullable = false,length = 100) ///Hibernate
    //Validadores regulares
    @Pattern(regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@”]+(\\.[^<>()\\[\\]\\\\.,;:\\s@”]+)*)|(“.+”))@((\\[[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}])|(([a-zA-Z\\-0–9]+\\.)+[a-zA-Z]{2,}))$" ,message = "Escriba su correo de la manera correcta")
    @NotEmpty(message = "No puede estar vacio")
    private String email; //"email": "correo2@mail.com" JPA Persiten los datos,

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY) ///Hibernate
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "direccion_id", nullable = false)
    @NotNull(message = "No puede estar vacio")
    private Direccion direccion;

    /*
    "direccion": {
            "id": 2,
            "calle": "Avenida2",
            "ciudad": "Ecatepec2",
            "codigoPostal": "55000"
        }
     */


}
