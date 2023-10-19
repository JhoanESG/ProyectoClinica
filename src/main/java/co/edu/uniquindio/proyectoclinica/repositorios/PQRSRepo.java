package co.edu.uniquindio.proyectoclinica.repositorios;

import co.edu.uniquindio.proyectoclinica.model.entities.PQRS;
import co.edu.uniquindio.proyectoclinica.model.entities.Paciente;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PQRSRepo extends JpaRepository<PQRS,Integer> {

    //Busca PQRS segun un paciente y los estados del pqrs que conincidan
    List<PQRS> findByConsultaCitaPacienteAndEstadoIn(Paciente paciente, List<EstadoPQRS> estados);

}
