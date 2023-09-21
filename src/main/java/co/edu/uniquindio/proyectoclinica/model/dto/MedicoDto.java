package co.edu.uniquindio.proyectoclinica.model.dto;

import java.time.LocalDateTime;

public record MedicoDto(
        String codigo,
        String nombre,
        String apellido,
        String telefono,
        String ciudad,
        LocalDateTime inicioJornada,
        LocalDateTime finJornada
) {

}


