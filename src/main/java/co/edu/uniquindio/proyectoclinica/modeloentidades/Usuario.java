package co.edu.uniquindio.proyectoclinica.modeloentidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    private String cedula;
    private String email;
    private String contrasena;
}
