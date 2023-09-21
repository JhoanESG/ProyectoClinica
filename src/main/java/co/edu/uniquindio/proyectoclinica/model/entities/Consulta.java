package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Consulta implements Serializable {

    @Id
    private String id;

    private String sintomas;
    private String diagnostico;
    private String tratamiento;
    private String notas;

    @OneToMany(mappedBy = "consulta")
    private List<ConsultaMedicamento> consultaMedicamentos;

    @OneToMany(mappedBy = "consulta")
    private List<PQRS> listaPqrs;

    @OneToOne
    private Cita cita;

}
