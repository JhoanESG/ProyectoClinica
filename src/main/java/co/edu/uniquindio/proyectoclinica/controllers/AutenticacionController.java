package co.edu.uniquindio.proyectoclinica.controllers;

import co.edu.uniquindio.proyectoclinica.model.dto.LoginDto;
import co.edu.uniquindio.proyectoclinica.model.dto.MensajeDto;
import co.edu.uniquindio.proyectoclinica.model.dto.TokenDto;
import co.edu.uniquindio.proyectoclinica.model.dto.paciente.CrearPacienteDto;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.AuthenticationService;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    private final AuthenticationService authenticationService;
    private final PacienteService pacienteService;

    @PostMapping("/login")
    public ResponseEntity<MensajeDto<TokenDto>> login (@Valid @RequestBody LoginDto loginDto) throws Exception{

        TokenDto tokenDto= authenticationService.login(loginDto);
        return ResponseEntity.ok().body(new MensajeDto<>(false,tokenDto));

    }
    @PostMapping("/registrarse")
    public ResponseEntity<MensajeDto<String>> registrar(@Valid @RequestBody CrearPacienteDto crearPacienteDto) throws Exception{
        pacienteService.registrar(crearPacienteDto);
        return ResponseEntity.ok().body(new MensajeDto<>(false,"Paciente registrado correctamente"));
    }
}
