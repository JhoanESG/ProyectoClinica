package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Mensaje implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

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
