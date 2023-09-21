package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Mensaje implements Serializable {

    @Id
    private String id;
    private String texto;
    private LocalDateTime fecha;
    @ManyToOne
    private PQRS pqrs;

    @ManyToOne
    private Administrador administrador;

}
