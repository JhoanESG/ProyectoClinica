package co.edu.uniquindio.proyectoclinica.model.dto;

public record MensajeDto<T>(
        boolean error,
        T respuesta
) {
}
