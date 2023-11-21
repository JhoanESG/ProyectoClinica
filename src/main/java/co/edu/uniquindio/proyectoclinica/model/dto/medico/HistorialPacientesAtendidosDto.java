package co.edu.uniquindio.proyectoclinica.model.dto.medico;

import java.time.LocalDateTime;

public record HistorialPacientesAtendidosDto(
        String nombrePaciente,
        String idPaciente,
        LocalDateTime fecha,
        String motivo,
        int codigoConsulta
) {
}
