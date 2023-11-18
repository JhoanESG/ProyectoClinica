package co.edu.uniquindio.proyectoclinica.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank @Email
        String email,
        @NotBlank
        String contrasena
) {
}
