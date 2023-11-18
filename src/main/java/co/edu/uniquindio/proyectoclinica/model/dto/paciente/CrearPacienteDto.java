package co.edu.uniquindio.proyectoclinica.model.dto.paciente;

import co.edu.uniquindio.proyectoclinica.model.enums.Ciudad;
import co.edu.uniquindio.proyectoclinica.model.enums.Eps;
import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.enums.TipoSangre;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CrearPacienteDto (

        String cedula,
        String nombre,
        String apellido,
        String telefono,
        Ciudad ciudad,
        String email,
        String contrasena,
        String alergias,
        String foto,
        TipoSangre tipoSangre,
        Eps eps,
        LocalDate fechaNacimiento
) {
}
