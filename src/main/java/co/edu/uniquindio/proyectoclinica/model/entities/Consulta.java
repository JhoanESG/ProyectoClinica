package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Consulta implements Serializable {

    @Id
    private String id;
    private String idCita;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;
    private String notas;

    @OneToMany(mappedBy = "consulta")
    private List<ConsultaMedicamento> consultaMedicamentos;

    @OneToMany(mappedBy = "consulta")
    private List<PQRS> listaPqrs;

    @ManyToOne
    private Cita cita;

}
