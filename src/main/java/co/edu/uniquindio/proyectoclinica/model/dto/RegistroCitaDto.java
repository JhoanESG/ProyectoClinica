package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;

import java.time.LocalDateTime;

public record RegistroCitaDto(
        Especialidad especialidad,
        LocalDateTime fechaCita,
        LocalDateTime hora,
        String motivo
) {
}
