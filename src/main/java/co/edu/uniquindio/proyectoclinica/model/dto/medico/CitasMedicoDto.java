package co.edu.uniquindio.proyectoclinica.model.dto.medico;

import java.time.LocalDateTime;

public record CitasMedicoDto(
        int idCita,
        String nombrePaciente,
        String idPaciente,
        LocalDateTime hora,
        String motivo
) {
}
