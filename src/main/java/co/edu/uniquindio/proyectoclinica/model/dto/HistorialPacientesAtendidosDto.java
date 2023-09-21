package co.edu.uniquindio.proyectoclinica.model.dto;

import java.time.LocalDateTime;

public record HistorialPacientesAtendidosDto(
        String nombrePaciente,
        String idPaciente,
        LocalDateTime fecha,
        LocalDateTime hora,
        String motivo
) {
}
