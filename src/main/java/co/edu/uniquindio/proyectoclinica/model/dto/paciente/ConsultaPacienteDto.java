package co.edu.uniquindio.proyectoclinica.model.dto.paciente;


import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;

import java.time.LocalDateTime;

public record ConsultaPacienteDto(
        int idConsulta,
        LocalDateTime fechaConsulta,
        LocalDateTime fechaSolicitud,
        Especialidad especialidad

) {
}
