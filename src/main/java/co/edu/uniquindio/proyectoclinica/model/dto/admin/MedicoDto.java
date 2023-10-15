package co.edu.uniquindio.proyectoclinica.model.dto.admin;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;

import java.time.LocalDateTime;

public record MedicoDto(
        String cedula,
        String nombre,
        String apellido,
        String telefono,
        String ciudad,
        Especialidad especialidad,
        String email,
        String foto,

        LocalDateTime inicioJornada,
        LocalDateTime finJornada
) {

}


