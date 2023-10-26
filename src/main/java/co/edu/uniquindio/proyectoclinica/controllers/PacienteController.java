package co.edu.uniquindio.proyectoclinica.controllers;

import co.edu.uniquindio.proyectoclinica.model.services.interfaces.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;


}
