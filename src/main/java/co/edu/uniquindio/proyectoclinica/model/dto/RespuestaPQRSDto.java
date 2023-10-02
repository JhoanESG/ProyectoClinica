package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.entities.Usuario;

import java.time.LocalDateTime;

public record RespuestaPQRSDto(
        int codigo,
        String descripcion,

        Usuario usuario
) {
}
