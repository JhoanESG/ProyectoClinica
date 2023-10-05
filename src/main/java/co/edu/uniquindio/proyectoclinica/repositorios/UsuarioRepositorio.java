package co.edu.uniquindio.proyectoclinica.repositorios;

import co.edu.uniquindio.proyectoclinica.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario,String> {
}
