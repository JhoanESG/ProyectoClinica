package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyectoclinica.model.enums.TipoPQRS;

import java.time.LocalDateTime;

public record DetallePQRSmedicoDto(
        int idPQRS,
        String ccPaciente,
        String asunto,
        EstadoPQRS estado,
        TipoPQRS tipoPQRS,
        LocalDateTime fechaCita,
        LocalDateTime horacita,
        String medico,
        Especialidad especialidad,
        LocalDateTime fechaSolicitud,
        String motivo


) {
}
