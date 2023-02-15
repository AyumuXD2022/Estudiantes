package mx.com.escuela.escuelaBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Table(name = "direcciones")
@Entity
@Data
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    ///________ 255
    ///WILMER -> 6 lo que resta es un espacio que vas a ocupar null
    @Column(length = 60,nullable = false)
    @Pattern(regexp = "[a-zA-Z1-9À-ÖØ-öø-ÿ]+\\.?(( |\\-)[a-zA-Z1-9À-ÖØ-öø-ÿ]+\\.?)* (((#|[nN][oO]\\.?) ?)?\\d{1,4}(( ?[a-zA-Z0-9\\-]+)+)?)", message = "Escribe bien tu direccion")
    private String calle;

    @Column(length = 30,nullable = false)
    @Pattern(regexp = "[a-zA-Z1-9À-ÖØ-öø-ÿ]+\\.?(( |\\-)[a-zA-Z1-9À-ÖØ-öø-ÿ]+\\.?)*", message = "Escriba bien su nombre")
    private String ciudad;

    @Column(length = 10,name = "codigo_postal",nullable = false)
    @Pattern(regexp = "[0-9]{1,6}",message = "Escriba su codigo postal de la manera correcta")
    private String codigoPostal;


}
