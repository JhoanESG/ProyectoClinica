package co.edu.uniquindio.proyectoclinica.model.dto.medico;

import co.edu.uniquindio.proyectoclinica.model.enums.EstadoDiaLibre;

import java.time.LocalDate;

public record ItemDiaLibre(
        int id,
        LocalDate fecha,
        String estadoDiaLibre,
        String idMedico

) {
}
