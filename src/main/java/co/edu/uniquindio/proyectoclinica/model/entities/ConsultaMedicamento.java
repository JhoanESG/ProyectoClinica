package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Setter
@Getter
//@Data
@NoArgsConstructor
public class ConsultaMedicamento implements Serializable {

    @Id
    private int codigo;

    @ManyToOne
    private Consulta consulta;

    @ManyToOne
    private Medicamento medicamento;

    @Column(nullable = false)
    private int cantidad;
}
