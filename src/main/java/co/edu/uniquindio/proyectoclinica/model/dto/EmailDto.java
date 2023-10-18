package co.edu.uniquindio.proyectoclinica.model.dto;

public record EmailDto(
        String destinatario,
        String asunto,
        String cuerpo) {
}
