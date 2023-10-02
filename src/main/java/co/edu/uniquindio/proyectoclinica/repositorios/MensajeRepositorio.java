package co.edu.uniquindio.proyectoclinica.repositorios;

import co.edu.uniquindio.proyectoclinica.model.entities.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepositorio extends JpaRepository<Mensaje,String> {
}
