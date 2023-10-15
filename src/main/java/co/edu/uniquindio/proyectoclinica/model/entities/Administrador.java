package co.edu.uniquindio.proyectoclinica.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Administrador extends Usuario implements Serializable {


}
