package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@MappedSuperclass
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DetalleUsuario extends Usuario implements Serializable {

    @Column(nullable = false, length = 10)
    private String telefono;

    @Column(nullable = false, length = 20)
    private String ciudad;

    @Column(nullable = false)
    private String foto;

}
