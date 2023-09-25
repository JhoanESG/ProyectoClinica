package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;

import java.time.LocalDateTime;

public record PacienteInicioDto (
        String nombreCita,
        LocalDateTime fechaCita,
        EstadoCita estadoCita,
        String motivoPQRS,
        LocalDateTime fechaPQRS,
        EstadoPQRS estadoPQRS
){
}
