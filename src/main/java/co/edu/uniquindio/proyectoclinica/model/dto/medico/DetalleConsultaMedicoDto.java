package co.edu.uniquindio.proyectoclinica.model.dto.medico;

import co.edu.uniquindio.proyectoclinica.model.dto.MedicamentoDto;
import co.edu.uniquindio.proyectoclinica.model.enums.Ciudad;
import co.edu.uniquindio.proyectoclinica.model.enums.Eps;
import co.edu.uniquindio.proyectoclinica.model.enums.TipoSangre;

import java.util.List;

public record DetalleConsultaMedicoDto(
        String nombrePaciente,
        String apellidoPaciento,
        Ciudad ciudad,
        String telefono,
        TipoSangre tipoSangre,
        String alergia,
        Eps eps,
        String sintomas,
        String diagnostico,
        String tratamiento,
        String notas,
        List<MedicamentoDto> medicamentos
) {
}
