package co.edu.uniquindio.proyectoclinica.model.entities;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Cita implements Serializable {

    @Id
    private int id;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCita;
    private String motivo;
    private int estado;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    private Especialidad especialidadMedico;
    private EstadoCita estadoCita;

    @OneToMany(mappedBy = "cita")
    private List<Consulta> listaConsultas;
}
