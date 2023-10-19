package co.edu.uniquindio.proyectoclinica.model.dto.paciente;

import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyectoclinica.model.enums.TipoPQRS;

import java.time.LocalDateTime;

public record DetallePQRSdto (
        int idPQRS,
        int idConsulta,
        String asunto,
        LocalDateTime fechaCreacion,
        TipoPQRS tipoPQRS,
        EstadoPQRS estadoPQRS,
        String mensaje
){
}
