package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
//@Data
@Setter
@Getter
@NoArgsConstructor
public class Mensaje implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    private PQRS pqrs;

    @ManyToOne
    private Administrador administrador;

    @OneToOne
    private Mensaje mensaje;


}
