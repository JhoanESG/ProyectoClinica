package co.edu.uniquindio.proyectoclinica.model.dto;

public record EmailDto(
        String mensaje,
        String para,
        String asunto,
        String de) {
}
