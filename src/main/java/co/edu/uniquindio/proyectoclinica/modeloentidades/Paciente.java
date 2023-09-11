package co.edu.uniquindio.proyectoclinica.modeloentidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paciente {

    @Id
    private String cedulaDetalle;
    private LocalDateTime fechaNacimiento;
    private String alergias;
    private Eps Eps;
    private TipoSangre tipoSangre;
}
