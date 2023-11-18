package co.edu.uniquindio.proyectoclinica.controllers;

import co.edu.uniquindio.proyectoclinica.model.dto.MensajeDto;
import co.edu.uniquindio.proyectoclinica.model.enums.Ciudad;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.ClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clinica")
public class ClinicaController {

    private final ClinicaService clinicaService;

    @GetMapping("/lista-ciudades")
    public ResponseEntity<MensajeDto<List<String>>> listarCiudades()throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,clinicaService.obtenerListaCiudades()));
    }

    @GetMapping("/lista-EPS")
    public ResponseEntity<MensajeDto<List<String>>> listarEPS() throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,clinicaService.obtenerListaEps()));
    }


    @GetMapping("/lista-tipo-sangre")
    public ResponseEntity<MensajeDto<List<String>>> listarTipoSangre() throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,clinicaService.obtenerListaTipoSangre()));
    }

    @GetMapping("/lista-especialidades")
    public ResponseEntity<MensajeDto<List<String>>> listarEspecialidades() throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,clinicaService.obtenerListaEspecialidades()));
    }
}
