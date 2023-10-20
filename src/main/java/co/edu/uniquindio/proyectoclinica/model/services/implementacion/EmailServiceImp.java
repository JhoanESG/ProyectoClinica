package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.dto.EmailDto;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImp implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void enviarEmail(EmailDto emailDto) throws Exception {

        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        helper.setTo(emailDto.destinatario());
        helper.setSubject(emailDto.asunto());
        helper.setText(emailDto.cuerpo());
        helper.setFrom("no_reply@dominio.com");

        javaMailSender.send(mensaje);

    }
}
