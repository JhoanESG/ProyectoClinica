package co.edu.uniquindio.proyectoclinica.modeloentidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class ConsultaMedicamento implements Serializable {

    @Id
    private String idConsulta;
    private String idMedicamento;
    private int cantidad;
}
