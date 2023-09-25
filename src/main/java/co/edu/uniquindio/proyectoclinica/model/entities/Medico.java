package co.edu.uniquindio.proyectoclinica.model.entities;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Medico extends DetalleUsuario implements Serializable {

    @Column(nullable = false)
    private Especialidad especialidad;

    @Column(nullable = false)
    private LocalDateTime horaInicio;

    @Column(nullable = false)
    private LocalDateTime horaFin;

    @OneToMany(mappedBy = "medico")
    private List<Cita> listaCitas;


}
