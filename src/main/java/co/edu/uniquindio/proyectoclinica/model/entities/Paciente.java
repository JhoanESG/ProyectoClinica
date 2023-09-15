package co.edu.uniquindio.proyectoclinica.model.entities;

import co.edu.uniquindio.proyectoclinica.model.enums.TipoSangre;
import co.edu.uniquindio.proyectoclinica.model.enums.Eps;
import jakarta.persistence.Entity;

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


    private LocalDateTime fechaNacimiento;
    private String alergias;
    private  Eps eps;
    private TipoSangre tipoSangre;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> listaCitas;

}
