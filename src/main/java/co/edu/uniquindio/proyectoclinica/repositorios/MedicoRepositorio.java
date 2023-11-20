package co.edu.uniquindio.proyectoclinica.repositorios;

import co.edu.uniquindio.proyectoclinica.model.entities.Medico;
import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepositorio extends JpaRepository<Medico,String> {

    //@Query("select m from medico m where m.email = :email")
    Medico findByEmail(String email);

    //@Query("select m from medico m where m.cedula = :cedula")
    Medico findByCedula(String cedula);

    List<Medico> findByEspecialidad(Especialidad especialidad);
}
