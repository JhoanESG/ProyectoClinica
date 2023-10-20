package co.edu.uniquindio.proyectoclinica.controllers;

import co.edu.uniquindio.proyectoclinica.model.services.implementacion.PacienteServiceImp;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/mail")
public class TestController {
    @Autowired
    PacienteServiceImp pacienteServiceImp;

    @PostMapping("/enviarLink")
    public ResponseEntity<String> cambiarContrasena(@RequestBody Map<String, Object> loginData) throws Exception {
        String correo = (String) loginData.get("correo");

        pacienteServiceImp.enviarLinkRecuperacion(correo);

       return ResponseEntity.ok("Contraseña Cambiada con éxito");
    }
}
