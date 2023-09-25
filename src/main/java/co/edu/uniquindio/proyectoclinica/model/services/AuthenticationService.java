package co.edu.uniquindio.proyectoclinica.model.services;

import co.edu.uniquindio.proyectoclinica.model.dto.LoginDTO;

public interface AuthenticationService {

    String login (LoginDTO loginDto) throws Exception;


}
