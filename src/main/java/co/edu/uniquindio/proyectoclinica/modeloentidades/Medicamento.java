package co.edu.uniquindio.proyectoclinica.modeloentidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Medicamento implements Serializable {

    @Id
    private String id;
    private String nombre;
    private String posologia;
}
