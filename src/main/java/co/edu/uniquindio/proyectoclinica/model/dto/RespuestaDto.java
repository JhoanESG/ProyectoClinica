package co.edu.uniquindio.proyectoclinica.model.dto;

import java.time.LocalDateTime;

public record RespuestaDto(
        int codigoMensaje,
        String mensaje,
        String ccUsuario,
        LocalDateTime fecha
) {
}
