package co.edu.uniquindio.proyectoclinica.model.dto.paciente;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;

import java.time.LocalDateTime;

public record DetalleConsultaDto(
        int idConsulta,
        LocalDateTime fechaConsulta,
        LocalDateTime fechaSolicitud,
        Especialidad especialidad,
        String nombreMedico,
        String motivo,
        String diagnostico,
        String tratamiento,
        String medicamento

) {
}
