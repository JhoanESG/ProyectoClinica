package co.edu.uniquindio.proyectoclinica.controllers;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.MedicoCrearDto;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.MedicoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.PQRSAdminDto;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.CitasMedicoDto;
import co.edu.uniquindio.proyectoclinica.model.entities.Mensaje;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.AdministradorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
@RequiredArgsConstructor
@RestController
public class AdminController {
    private final AdministradorService administradorService;

    @PostMapping("/crear-medico")
    public ResponseEntity<MensajeDto<String>> crearMedico(@Valid @RequestBody MedicoCrearDto medicoDto) throws Exception{
        administradorService.crearMedico(medicoDto);
        return ResponseEntity.ok().body(new MensajeDto<>(false,"Medico creado con exito"));
    }

    @PutMapping("/editar-medico")
    public ResponseEntity<MensajeDto<String>> actualizarMedico(@Valid @RequestBody MedicoDto medicoDto) throws Exception{
        administradorService.actualizarMedico(medicoDto);
        return ResponseEntity.ok().body(new MensajeDto<>(false,"Medico actualizado correctamente"));
    }

    @DeleteMapping("eliminar/{codigo}")
    public ResponseEntity<MensajeDto<String>> eliminarMedico (@PathVariable String codigo) throws Exception{
        administradorService.eliminarMedico(codigo);
        return ResponseEntity.ok().body(new MensajeDto<>(false,"Medico eliminado correctamente"));
    }

    @GetMapping("/listar-medicos")
    public ResponseEntity<MensajeDto<List<MedicoListarDto>>> listarMedicos()throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,administradorService.listarMedicos()));
    }

    @GetMapping("/detalle-medico/{codigo}")
    public ResponseEntity<MensajeDto<MedicoDto>> obtenerMedico(@PathVariable String codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false, administradorService.obtenerMedico(codigo)));
    }

    @GetMapping("/listar-PQRS")
    public ResponseEntity<MensajeDto<List<PQRSAdminDto>>> listarPQRS() throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,administradorService.listarPQRS()));
    }

    @GetMapping("/detalle-PQRS/{codigo}")
    public ResponseEntity<MensajeDto<DetallePQRSmedicoDto>> verDetallePQRS( @PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,administradorService.verDetallePQRS(codigo)));
    }

    @GetMapping("/respuestas")
    public ResponseEntity<MensajeDto<List<RespuestaDto>>> convertirRespuestas (@Valid @RequestBody List<Mensaje> mensajes) throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,administradorService.convertirRespuestasDto(mensajes)));
    }

    @PostMapping("/PQRS/responder")
    public ResponseEntity<MensajeDto<String>> responderPQRS(@Valid @RequestBody RespuestaPQRSDto respuestaPQRSDto) throws Exception{
        administradorService.responderPQRS(respuestaPQRSDto);
        return ResponseEntity.ok().body(new MensajeDto<>(false, "Respuesta registrada"));
    }

    @PutMapping("/PQRS/cambiar-estado/{codigo}")
    public ResponseEntity<MensajeDto<String>> cambiarEstadoPQRS(@PathVariable int codigo, @RequestParam EstadoPQRS estadoPQRS) throws Exception{
        administradorService.cambiarEstadoPqrs(codigo,estadoPQRS);
        return ResponseEntity.ok().body(new MensajeDto<>(false,"Estado PQRS cambiado con exito"));
    }

    @GetMapping("/listar-citas")
    public ResponseEntity<MensajeDto<List<CitasMedicoDto>>> listarCitas () throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false, administradorService.listarCitasMedico()));
    }

}
