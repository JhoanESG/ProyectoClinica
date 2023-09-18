package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;

public record PQRSAdminDto(
        String id,
        String cita,
        String asunto,
        String ccPaciente,
        EstadoPQRS estadoPQRS

) {
}
