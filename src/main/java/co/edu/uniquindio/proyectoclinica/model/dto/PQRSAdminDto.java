package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyectoclinica.model.enums.TipoPQRS;

public record PQRSAdminDto(
        int id,
        int cita,
        String asunto,
        String ccPaciente,
        TipoPQRS tipoPQRS,
        EstadoPQRS estadoPQRS

) {
}
