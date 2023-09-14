package co.edu.uniquindio.proyectoclinica.model.entities;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    private String cedula;
    private String email;
    private String contrasena;

}

