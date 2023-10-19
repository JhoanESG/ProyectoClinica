package co.edu.uniquindio.proyectoclinica.model.dto.medico;

import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;

import java.time.LocalDateTime;

public record CitaMedicoDTo(
        String paciente,
        EstadoCita estadoCita,
        LocalDateTime hora) {
}
