package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;

import java.time.LocalDateTime;

public record MedicoListarDto(
        int codigo,
        String nombre,
        String apellido,
        String telefono,
        String ciudad,
        Especialidad especialidad,
        LocalDateTime inicioJornada,
        LocalDateTime finJornada
) {
}
