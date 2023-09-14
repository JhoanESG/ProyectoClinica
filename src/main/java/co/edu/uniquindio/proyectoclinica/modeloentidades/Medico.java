package co.edu.uniquindio.proyectoclinica.modeloentidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Medico extends DetalleUsuario implements Serializable {

    @Id
    private String cedula;
    private Especialidad especialidad;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;

    @OneToMany(mappedBy = "medico")
    private List<Cita> listaCitas;


}
