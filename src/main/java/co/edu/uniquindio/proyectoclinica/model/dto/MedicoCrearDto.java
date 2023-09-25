package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;

import java.time.LocalDateTime;

public record MedicoCrearDto(
        String cedula,
        String nombre,
        String apellido,
        String telefono,
        String ciudad,
        String email,
        String contrasena,
        Especialidad especialidad,
        LocalDateTime horaInicio,
        LocalDateTime horaFIn
) {
}
