package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.entities.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record RespuestaPQRSDto(
        int codigoPqrs,
        @NotBlank
        String descripcion,

        String usuario
) {
}
