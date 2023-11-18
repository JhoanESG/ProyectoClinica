package co.edu.uniquindio.proyectoclinica.model.dto.paciente;

import co.edu.uniquindio.proyectoclinica.model.enums.Ciudad;
import co.edu.uniquindio.proyectoclinica.model.enums.Eps;
import co.edu.uniquindio.proyectoclinica.model.enums.TipoSangre;

import java.time.LocalDateTime;

public record ActualizarPacienteDto (
        String cedula,
        String nombre,
        String apellido,
        String telefono,
        Ciudad ciudad,
        String email,
        String alergias,
        TipoSangre tipoSangre,
        Eps eps,
        String foto
) {
}
