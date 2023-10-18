package co.edu.uniquindio.proyectoclinica.model.dto.paciente;

import co.edu.uniquindio.proyectoclinica.model.enums.TipoPQRS;

public record CrearPQRSdto(
        String asunto,
        int idConsulta,
        TipoPQRS tipoPQRS,
        String descripcion
) {
}
