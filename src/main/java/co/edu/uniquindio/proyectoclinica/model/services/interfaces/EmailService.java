package co.edu.uniquindio.proyectoclinica.model.services.interfaces;

import co.edu.uniquindio.proyectoclinica.model.dto.EmailDto;

public interface EmailService {

    String enviarServicio(EmailDto emailDto) throws Exception;
}