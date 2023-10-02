package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;

public record PQRSAdminDto(
        int id,
        int cita,
        String asunto,
        String ccPaciente,
        EstadoPQRS estadoPQRS

) {
}
