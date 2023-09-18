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
    @Column(nullable = false, length = 10)
    private String cedula;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private String contrasena;

}

