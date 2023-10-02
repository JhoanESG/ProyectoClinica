package co.edu.uniquindio.proyectoclinica.model.entities;

import co.edu.uniquindio.proyectoclinica.model.enums.TipoSangre;
import co.edu.uniquindio.proyectoclinica.model.enums.Eps;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paciente extends DetalleUsuario implements Serializable {


    @Column(nullable = false)
    private LocalDateTime fechaNacimiento;

    @Column(nullable = false, length = 50)
    private String alergias;

    @Column(nullable = false)
    private  Eps eps;

    @Column(nullable = false)
    private TipoSangre tipoSangre;


    @OneToMany(mappedBy = "paciente")
    private List<Cita> listaCitas;

}
