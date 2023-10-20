package co.edu.uniquindio.proyectoclinica.model.entities;


import co.edu.uniquindio.proyectoclinica.model.enums.EstadoUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

    @OneToMany(mappedBy = "usuario")
    private List<Mensaje> mensajes;

}

