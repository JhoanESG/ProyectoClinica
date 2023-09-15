package co.edu.uniquindio.proyectoclinica.model.entities;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Medico extends DetalleUsuario implements Serializable {

    private Especialidad especialidad;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;

    @OneToMany(mappedBy = "medico")
    private List<Cita> listaCitas;


}
