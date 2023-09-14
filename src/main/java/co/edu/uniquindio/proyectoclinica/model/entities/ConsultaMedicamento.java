package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class ConsultaMedicamento implements Serializable {

    @Id
    @ManyToOne
    private Consulta consulta;

    @ManyToOne
    private Medicamento medicamento;

    private int cantidad;
}
