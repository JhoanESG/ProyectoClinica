package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Administrador extends Usuario implements Serializable {

    @OneToMany(mappedBy = "administrador")
    private List<Mensaje> listaMensajes;

}
