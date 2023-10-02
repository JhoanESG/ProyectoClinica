package co.edu.uniquindio.proyectoclinica.model.entities;


import co.edu.uniquindio.proyectoclinica.model.enums.EstadoUsuario;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 10, updatable = false)
    private String cedula;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private EstadoUsuario estado;

}

