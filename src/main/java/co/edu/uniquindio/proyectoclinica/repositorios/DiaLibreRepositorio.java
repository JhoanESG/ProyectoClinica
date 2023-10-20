package co.edu.uniquindio.proyectoclinica.repositorios;

import co.edu.uniquindio.proyectoclinica.model.entities.DiaLibre;
import co.edu.uniquindio.proyectoclinica.model.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiaLibreRepositorio extends JpaRepository<DiaLibre,Integer> {
    List<DiaLibre> findByMedicoAndDiaGreaterThanEqual(Medico medico, LocalDate fecha);
}
