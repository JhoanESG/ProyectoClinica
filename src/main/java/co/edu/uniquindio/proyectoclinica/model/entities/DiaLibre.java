package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class DiaLibre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @NotNull
    private LocalDateTime dia;

    @ManyToOne
    private Medico medico;
}
