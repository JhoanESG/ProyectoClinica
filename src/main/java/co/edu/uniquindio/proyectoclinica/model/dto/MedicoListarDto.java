package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;

import java.time.LocalDateTime;

public record MedicoListarDto(
        String codigo,
        String nombre,
        String apellido,
        Especialidad especialidad

)
{
}
