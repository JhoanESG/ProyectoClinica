package co.edu.uniquindio.proyectoclinica.model.dto.medico;

import java.time.LocalDate;

public record DiaLibreDto(
        LocalDate fecha,
        String idMedico

) {
}
