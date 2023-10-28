package co.edu.uniquindio.proyectoclinica.controllers;

import co.edu.uniquindio.proyectoclinica.model.dto.MensajeDto;
import co.edu.uniquindio.proyectoclinica.model.dto.RespuestaPQRSDto;
import co.edu.uniquindio.proyectoclinica.model.dto.paciente.*;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

   @GetMapping("/detalle/{codigo}")
    public ResponseEntity<MensajeDto<ActualizarPacienteDto>> detalle(@PathVariable String codigo)throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,pacienteService.obtenerPaciente(codigo)));
   }

    @PutMapping("/editar-perfil")
    public String  editarPerfil(@Valid @RequestBody ActualizarPacienteDto actualizarPacienteDto)throws Exception{
        return pacienteService.editarPerfil(actualizarPacienteDto);
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDto<String>> eliminarCuenta(@PathVariable String codigo) throws Exception{
       pacienteService.eliminarCuenta(codigo);
       return ResponseEntity.ok().body(new MensajeDto<>(false,"Paciente eliminado correctamente"));
    }

    @PostMapping("/agendar-cita")
    public ResponseEntity<MensajeDto<String>> agendarCita(@Valid @RequestBody RegistroCitaDto registroCitaDto) throws Exception{
       pacienteService.agendarCita(registroCitaDto);
       return ResponseEntity.ok().body(new MensajeDto<>(false, "Cita agendada correctamente"));
    }

    @PostMapping("/crear-PQRS")
    public ResponseEntity<MensajeDto<String>> crearPQRS(@Valid @RequestBody CrearPQRSdto crearPQRSdto) throws Exception{
       pacienteService.crearPQRS(crearPQRSdto);
       return ResponseEntity.ok().body(new MensajeDto<>(false,"PQRS creado correctamente"));
    }

    @GetMapping("/detalle-PQRS/{codigo}")
    public ResponseEntity<MensajeDto<DetallePQRSdto>> detallePQRS(@PathVariable int codigo) throws Exception{
       return ResponseEntity.ok().body(new MensajeDto<>(false, pacienteService.detallePQRS(codigo)));
    }

    @GetMapping("detalle-cita/{codigo}")
    public ResponseEntity<MensajeDto<DetalleCitaPacienteDto>> detalleCita(@PathVariable int codigo) throws Exception{
       return ResponseEntity.ok().body(new MensajeDto<>(false,pacienteService.detalleCita(codigo)));
    }

    @GetMapping("/listar-PQRS/{codigo}")
    public ResponseEntity<MensajeDto<List<PQRSpacienteDto>>> listarPQRS(@PathVariable String codigo) throws Exception{
       return ResponseEntity.ok().body(new MensajeDto<>(false,pacienteService.listarPQRSpaciente(codigo)));
    }

    @GetMapping("/listar-citas/{codigo}")
    public ResponseEntity<MensajeDto<List<CitaPacienteDto>>> listarCitas(@PathVariable String codigo) throws Exception{
       return ResponseEntity.ok().body(new MensajeDto<>(false,pacienteService.listarCitasPaciente(codigo)));
    }

    @GetMapping("/listar-consultas/{codigo}")
    public ResponseEntity<MensajeDto<List<ConsultaPacienteDto>>> listarConsultas(@PathVariable String codigo) throws Exception{
       return ResponseEntity.ok().body(new MensajeDto<>(false,pacienteService.listarConsultasPaciente(codigo)));
    }

    @GetMapping("/detalle-consulta/{codigo}")
    public ResponseEntity<MensajeDto<DetalleConsultaDto>> verDetalleConsulta(@PathVariable int codigo) throws Exception{
       return ResponseEntity.ok().body(new MensajeDto<>(false,pacienteService.detalleConsulta(codigo)));
    }

    @PostMapping("/responder-PQRS")
    public ResponseEntity<MensajeDto<String>> responderPQRS(@Valid @RequestBody RespuestaPQRSDto respuestaPQRSDto)throws Exception{
       pacienteService.responderPQRS(respuestaPQRSDto);
       return ResponseEntity.ok().body(new MensajeDto<>(false,"Se registro la respuesta correctamente"));
    }




}
