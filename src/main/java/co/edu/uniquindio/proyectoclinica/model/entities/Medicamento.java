package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
//@Data
@Setter
@Getter
@NoArgsConstructor
public class Medicamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String posologia;

    @OneToMany(mappedBy = "medicamento")
    private List<ConsultaMedicamento> consultaMedicamentos;
}
