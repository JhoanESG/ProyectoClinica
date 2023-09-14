package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@MappedSuperclass
public class DetalleUsuario extends Usuario implements Serializable {

    @Id
    private String cedulaUsuario;
    private String telefono;
    private String ciudad;
    private String foto;

}
