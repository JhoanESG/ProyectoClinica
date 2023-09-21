package co.edu.uniquindio.proyectoclinica.model.dto;


import java.time.LocalDateTime;

public record DatosVistaPrevia(
        String nombre,
        String apellido,
        String cedula,
        LocalDateTime hora
) {
}
