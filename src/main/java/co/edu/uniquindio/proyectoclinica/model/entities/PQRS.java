package co.edu.uniquindio.proyectoclinica.model.entities;

import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyectoclinica.model.enums.TipoPQRS;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
//@Data
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PQRS implements Serializable {
    @Id
    @Column(nullable = false)
    private int id;

    @ManyToOne
    private Consulta consulta;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private EstadoPQRS estado;

    @Column(nullable = false)
    private TipoPQRS tipoPQRS;

    @Column(nullable = false)
    private String asunto;

    @Column(nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "pqrs")
    private List<Mensaje> listaMensajes;
}
