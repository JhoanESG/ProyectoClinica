package co.edu.uniquindio.proyectoclinica.model.dto;

import java.time.LocalDateTime;

public record CitasMedicoDto(
        String nombrePaciente,
        String idPaciente,
        LocalDateTime hora,
        String motivo
) {
}
