package co.edu.uniquindio.proyectoclinica.repositorios;

import co.edu.uniquindio.proyectoclinica.model.entities.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepositorio extends JpaRepository<Medicamento,Integer> {
    Medicamento findById(int id);
}
