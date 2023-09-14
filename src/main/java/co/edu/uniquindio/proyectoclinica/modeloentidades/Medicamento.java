package co.edu.uniquindio.proyectoclinica.modeloentidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Medicamento implements Serializable {

    @Id
    private String id;
    private String nombre;
    private String posologia;

    @OneToMany(mappedBy = "medicamento")
    private List<ConsultaMedicamento> consultaMedicamentos;
}
