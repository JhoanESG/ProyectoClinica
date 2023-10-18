package co.edu.uniquindio.proyectoclinica.model.services.interfaces;

import co.edu.uniquindio.proyectoclinica.model.dto.EmailDto;

public interface EmailService {

    void enviarEmail(EmailDto emailDto) throws Exception;
}
