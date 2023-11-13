package co.edu.uniquindio.proyectoclinica.repositorios;

import co.edu.uniquindio.proyectoclinica.model.entities.Consulta;
import co.edu.uniquindio.proyectoclinica.model.entities.Medico;
import co.edu.uniquindio.proyectoclinica.model.entities.Paciente;
import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepositorio extends JpaRepository<Consulta, Integer> {
    Consulta findConsultaById(int codigo);

    List<Consulta> findByCitaPaciente(Paciente paciente);

    Consulta findConsultaByCitaId(int idCita);

    List<Consulta> findByCitaPacienteAndCitaMedico(Paciente paciente, Medico medico);
    List<Consulta> findByCitaPacienteAndCita_FechaCita(Paciente paciente,LocalDateTime fecha);

}
