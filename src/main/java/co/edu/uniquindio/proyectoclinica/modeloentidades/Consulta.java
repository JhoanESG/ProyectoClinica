package co.edu.uniquindio.proyectoclinica.modeloentidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

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

}
