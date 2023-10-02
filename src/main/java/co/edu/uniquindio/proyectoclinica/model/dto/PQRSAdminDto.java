package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;

public record PQRSAdminDto(
        int id,
        String cita,
        String asunto,
        String ccPaciente,
        EstadoPQRS estadoPQRS

) {
}
