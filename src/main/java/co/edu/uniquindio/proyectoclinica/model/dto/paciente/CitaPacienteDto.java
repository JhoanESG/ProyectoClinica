package co.edu.uniquindio.proyectoclinica.model.dto.paciente;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;

import java.time.LocalDateTime;

public record CitaPacienteDto(
        Especialidad especialidad,
        LocalDateTime fecha,
        EstadoCita estadoCita,
        int codigo
) {
}
