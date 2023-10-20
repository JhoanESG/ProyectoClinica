package co.edu.uniquindio.proyectoclinica.model.dto.paciente;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;

import java.time.LocalDateTime;

public record DetalleCitaPacienteDto(
        int idCita,
        LocalDateTime fechaCita,
        String nombreMedico,
        Especialidad especialidad,
        LocalDateTime fechaSolicitud,
        String motivo
) {
}
