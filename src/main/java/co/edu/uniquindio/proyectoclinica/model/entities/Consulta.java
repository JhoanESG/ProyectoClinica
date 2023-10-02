package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Consulta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String sintomas;

    @Column(nullable = false)
    private String diagnostico;

    @Column(nullable = false)
    private String tratamiento;


    @Column(nullable = false)
    private String notas;

    @OneToMany(mappedBy = "consulta")
    private List<ConsultaMedicamento> consultaMedicamentos;

    @OneToMany(mappedBy = "consulta")
    private List<PQRS> listaPqrs;

    @OneToOne
    private Cita cita;

}
