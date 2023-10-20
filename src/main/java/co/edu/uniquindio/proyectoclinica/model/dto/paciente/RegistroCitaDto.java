package co.edu.uniquindio.proyectoclinica.model.dto.paciente;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegistroCitaDto(
        Especialidad especialidad,
        LocalDateTime fechaCita,
        String motivo,
        @NotNull
        String ccMedico,
        @NotNull
        String ccPaciente
) {
}
