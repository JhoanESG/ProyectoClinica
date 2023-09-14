package co.edu.uniquindio.proyectoclinica.modeloentidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Administrador extends Usuario implements Serializable {

    @Id
    private String idUsuario;

    @OneToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "administrador")
    private List<Mensaje> listaMensajes;
}
