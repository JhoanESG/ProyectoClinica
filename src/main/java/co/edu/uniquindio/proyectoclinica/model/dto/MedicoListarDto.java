package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;

import java.time.LocalDateTime;

public record MedicoListarDto(
        String cedula,
        String nombre,
        String apellido,
        Especialidad especialidad

)
{
}
