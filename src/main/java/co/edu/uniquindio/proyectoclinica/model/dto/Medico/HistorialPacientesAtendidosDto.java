package co.edu.uniquindio.proyectoclinica.model.dto.Medico;

import java.time.LocalDateTime;

public record HistorialPacientesAtendidosDto(
        String nombrePaciente,
        String idPaciente,
        LocalDateTime fecha,

        String motivo
) {
}
