package co.edu.uniquindio.proyectoclinica.model.entities;

import co.edu.uniquindio.proyectoclinica.model.enums.TipoSangre;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paciente extends DetalleUsuario implements Serializable {

    @Id
    private String cedulaDetalle;
    private LocalDateTime fechaNacimiento;
    private String alergias;
    private co.edu.uniquindio.proyectoclinica.model.enums.Eps Eps;
    private TipoSangre tipoSangre;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> listaCitas;

}
