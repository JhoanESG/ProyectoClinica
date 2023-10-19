package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.TipoSangre;

import java.util.List;

public record AtenderCitaDto(

        int idCita,
        String sintomas,
        String diagnostico,
        String tratamiento,
        String notas,
        List<MedicamentosDto> medicamentos
) {
}
