package co.edu.uniquindio.proyectoclinica.model.dto.medico;

import java.time.LocalDateTime;

public record CitasMedicoDto(
        String nombrePaciente,
        String idPaciente,
        LocalDateTime hora,
        String motivo
) {
}
