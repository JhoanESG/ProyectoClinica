package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
//@Data
@Setter
@Getter
@NoArgsConstructor
public class Administrador extends Usuario implements Serializable {

    @OneToMany(mappedBy = "administrador")
    private List<Mensaje> listaMensajes;

}
