package co.edu.uniquindio.proyectoclinica.model.entities;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Cita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @EqualsAndHashCode.Include
    private int id;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCita;
    private String motivo;
    private EstadoCita estado;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    private Especialidad especialidadMedico;
    private EstadoCita estadoCita;

    @OneToOne(mappedBy = "cita")
    private Consulta consulta;
}
