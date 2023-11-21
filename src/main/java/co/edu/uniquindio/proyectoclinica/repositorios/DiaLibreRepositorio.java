package co.edu.uniquindio.proyectoclinica.repositorios;

import co.edu.uniquindio.proyectoclinica.model.entities.DiaLibre;
import co.edu.uniquindio.proyectoclinica.model.entities.Medico;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoDiaLibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiaLibreRepositorio extends JpaRepository<DiaLibre,Integer> {
    List<DiaLibre> findByMedicoAndDiaGreaterThanEqual(Medico medico, LocalDate fecha);
    List<DiaLibre> findByMedicoAndEstadoDiaLibre(Medico medico, EstadoDiaLibre estadoDiaLibre);
    List<DiaLibre> findByMedicoAndEstadoDiaLibreAndDia(Medico medico, EstadoDiaLibre estadoDiaLibre, LocalDate fecha) throws Exception;
    DiaLibre findById(int dia)throws Exception;
    List<DiaLibre> findByMedicoAndEstadoDiaLibreAndDiaBefore(Medico medico, EstadoDiaLibre estado, LocalDate fecha) throws Exception;

}
