package co.edu.uniquindio.proyectoclinica.model.dto.Medico;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DiaLibreDto(
        LocalDateTime fecha,
        String idMedico

) {
}
