package co.edu.uniquindio.proyectoclinica.repositorios;

import co.edu.uniquindio.proyectoclinica.model.entities.Cita;
import co.edu.uniquindio.proyectoclinica.model.entities.Consulta;
import co.edu.uniquindio.proyectoclinica.model.entities.Medico;
import co.edu.uniquindio.proyectoclinica.model.entities.Paciente;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepo extends JpaRepository<Cita,Integer> {
    Cita findCitaById(int codigo);

    List<Cita> findByMedicoAndEstadoCita(Medico medico, EstadoCita estadoCita);
    List<Cita> findByMedicoAndFechaCita(Medico medico, LocalDateTime fecha);

    List<Cita> findByPacienteAndAndEstadoCita(Paciente paciente,EstadoCita estadoCita);
}
