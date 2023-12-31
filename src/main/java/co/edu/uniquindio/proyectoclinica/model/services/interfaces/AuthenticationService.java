package co.edu.uniquindio.proyectoclinica.model.services.interfaces;

import co.edu.uniquindio.proyectoclinica.model.dto.LoginDto;
import co.edu.uniquindio.proyectoclinica.model.dto.TokenDto;
import co.edu.uniquindio.proyectoclinica.model.entities.Usuario;
import org.springframework.stereotype.Service;


public interface AuthenticationService {

    TokenDto login (LoginDto loginDto) throws Exception;
}
