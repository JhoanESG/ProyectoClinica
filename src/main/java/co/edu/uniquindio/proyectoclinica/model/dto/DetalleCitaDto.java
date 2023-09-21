package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.Eps;
import co.edu.uniquindio.proyectoclinica.model.enums.TipoSangre;

import java.time.LocalDateTime;

public record DetalleCitaDto(
        String nombre,
        String apellido,
        String ciudad,
        String alergias,
        TipoSangre tipoSangre,
        Eps eps,
        LocalDateTime hora,
        String motivo
) {
}
