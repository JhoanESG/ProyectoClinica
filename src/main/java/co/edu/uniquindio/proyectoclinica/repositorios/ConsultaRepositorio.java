package co.edu.uniquindio.proyectoclinica.repositorios;

import co.edu.uniquindio.proyectoclinica.model.entities.Consulta;
import co.edu.uniquindio.proyectoclinica.model.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepositorio extends JpaRepository<Consulta, Integer> {
    Consulta findConsultaById(int codigo);

    List<Consulta> findByCitaPaciente(Paciente paciente);

}
