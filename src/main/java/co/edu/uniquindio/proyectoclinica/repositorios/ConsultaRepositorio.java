package co.edu.uniquindio.proyectoclinica.repositorios;

import co.edu.uniquindio.proyectoclinica.model.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepositorio extends JpaRepository<Consulta, Integer> {
    Consulta findConsultaById(int codigo);
}
