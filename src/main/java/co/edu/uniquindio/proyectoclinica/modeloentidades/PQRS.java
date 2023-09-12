package co.edu.uniquindio.proyectoclinica.modeloentidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class PQRS implements Serializable {
    @Id
    private String id;
    private String idConsulta;
    private LocalDateTime fechaCreacion;
    private EstadoPQRS estado;
    private TipoPQRS tipoPQRS;
    private String asunto;
    private String descripcion;
}
