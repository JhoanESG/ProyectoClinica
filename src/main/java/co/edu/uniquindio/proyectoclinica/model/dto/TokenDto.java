package co.edu.uniquindio.proyectoclinica.model.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenDto(
        @NotBlank
        String token
){
}
