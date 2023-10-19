package co.edu.uniquindio.proyectoclinica.model.dto.medico;

import java.time.LocalDateTime;

public record DiaLibreDto(
        LocalDateTime fecha,
        String idMedico

) {
}
