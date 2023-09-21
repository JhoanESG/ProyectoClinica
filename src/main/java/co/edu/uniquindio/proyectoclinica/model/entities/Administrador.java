package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Administrador extends Usuario implements Serializable {

    @OneToMany(mappedBy = "administrador")
    private List<Mensaje> listaMensajes;

}
