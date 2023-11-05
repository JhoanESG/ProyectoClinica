package co.edu.uniquindio.proyectoclinica.model.services.interfaces;

import co.edu.uniquindio.proyectoclinica.model.dto.EmailDto;
import org.springframework.stereotype.Service;


public interface EmailService {

    void enviarEmail(EmailDto emailDto) throws Exception;
}
