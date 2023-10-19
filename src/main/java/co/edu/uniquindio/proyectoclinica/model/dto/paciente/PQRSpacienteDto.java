package co.edu.uniquindio.proyectoclinica.model.dto.paciente;

import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;

import java.time.LocalDateTime;

public record PQRSpacienteDto(
        String asunto,
        LocalDateTime fecha,
        EstadoPQRS estadoPQRS
) {
}
