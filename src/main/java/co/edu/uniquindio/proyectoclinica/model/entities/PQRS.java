package co.edu.uniquindio.proyectoclinica.model.entities;

import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyectoclinica.model.enums.TipoPQRS;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class PQRS implements Serializable {
    @Id
    private String id;

    @ManyToOne
    private Consulta consulta;

    private LocalDateTime fechaCreacion;
    private EstadoPQRS estado;
    private TipoPQRS tipoPQRS;
    private String asunto;
    private String descripcion;

    @OneToMany(mappedBy = "pqrs")
    private List<Mensaje> listaMensajes;
}
