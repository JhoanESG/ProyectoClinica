package co.edu.uniquindio.proyectoclinica.model.dto.paciente;

import java.time.LocalDateTime;

public record VistaPreviaDto(
        String asunto,
        LocalDateTime fecha
) {
}
