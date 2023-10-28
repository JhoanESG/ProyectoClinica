package co.edu.uniquindio.proyectoclinica.controllers;

import co.edu.uniquindio.proyectoclinica.model.dto.DetalleCitaDto;
import co.edu.uniquindio.proyectoclinica.model.dto.MensajeDto;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.CitaMedicoDTo;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.CitasMedicoDto;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping("/citas-pendientes/{codigo}")
    public ResponseEntity<MensajeDto<List<CitaMedicoDTo>>> verCitasPendientes(@PathVariable String codigo)throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,medicoService.verCitasPendientes(codigo)));

    }

    @GetMapping("/listar-citas/{codigo}")
    public ResponseEntity<MensajeDto<List<CitasMedicoDto>>> listarCitas(@PathVariable String codigo)throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,medicoService.listarCitasMedico(codigo)));
    }

    @GetMapping("/detalle-cita/{codigo}")
    public ResponseEntity<MensajeDto<DetalleCitaDto>> verDetalleCita(@PathVariable int codigo)throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,medicoService.obtenerCita(codigo)));
    }



}
