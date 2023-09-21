package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.TipoSangre;

public record atenderCitaDto(
        String nombre,
        String apellido,
        String cc,
        String ciudad,
        String alergias,
        TipoSangre tipoSangre
) {
}
