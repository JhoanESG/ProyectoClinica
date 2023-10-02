package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Medicamento implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String posologia;

    @OneToMany(mappedBy = "medicamento")
    private List<ConsultaMedicamento> consultaMedicamentos;
}