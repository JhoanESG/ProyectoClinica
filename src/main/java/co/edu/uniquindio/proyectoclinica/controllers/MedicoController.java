package co.edu.uniquindio.proyectoclinica.controllers;

import co.edu.uniquindio.proyectoclinica.model.dto.AtenderCitaDto;
import co.edu.uniquindio.proyectoclinica.model.dto.DetalleCitaDto;
import co.edu.uniquindio.proyectoclinica.model.dto.MensajeDto;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.CitaMedicoDTo;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.CitasMedicoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.DiaLibreDto;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.HistorialPacientesAtendidosDto;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/radicar-consulta")
    public ResponseEntity<MensajeDto<String>> radicarConsulta(@Valid @RequestBody AtenderCitaDto atenderCitaDto) throws Exception{
        medicoService.radicarConsulta(atenderCitaDto);
        return ResponseEntity.ok().body(new MensajeDto<>(false,"Consulta registrada con exito"));
    }

    @GetMapping("/citas-atendidas/{idMedico}")
    public ResponseEntity<MensajeDto<List<HistorialPacientesAtendidosDto>>> listarCitasAtendidas(@PathVariable String idMedico)throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false, medicoService.listarCitasAtendidas(idMedico)));
    }

    @PostMapping("/registrar-dia-libre")
    public ResponseEntity<MensajeDto<String>> asignarDiaLibre(@Valid @RequestBody DiaLibreDto diaLibreDto) throws Exception{
        medicoService.asignarDiaLibre(diaLibreDto);
        return ResponseEntity.ok().body(new MensajeDto<>(false,"Se asigno el dia libre correctamente"));
    }

    @GetMapping("/ver-dias-libres/{idMedico}")
    public ResponseEntity<MensajeDto<List<DiaLibreDto>>> listarDiasLibres (@PathVariable String idMedico) throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,medicoService.listarDiasLibresMedico(idMedico)));
    }



}
