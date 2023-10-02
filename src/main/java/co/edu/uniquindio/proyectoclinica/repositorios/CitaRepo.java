package co.edu.uniquindio.proyectoclinica.repositorios;

import co.edu.uniquindio.proyectoclinica.model.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepo extends JpaRepository<Cita,Integer> {
}
