package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


//@Data
@MappedSuperclass
@NoArgsConstructor
@Setter
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DetalleUsuario extends Usuario implements Serializable {

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false,length = 50)
    private String apellido;

    @Column(nullable = false, length = 10)
    private String telefono;

    @Column(nullable = false, length = 20)
    private String ciudad;

    @Column(nullable = false)
    private String foto;

}
