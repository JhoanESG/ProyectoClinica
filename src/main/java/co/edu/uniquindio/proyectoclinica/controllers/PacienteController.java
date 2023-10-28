package co.edu.uniquindio.proyectoclinica.controllers;

import co.edu.uniquindio.proyectoclinica.model.dto.MensajeDto;
import co.edu.uniquindio.proyectoclinica.model.dto.paciente.ActualizarPacienteDto;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @PutMapping("/editar-perfil")
    public String  editarPerfil(@Valid @RequestBody ActualizarPacienteDto actualizarPacienteDto)throws Exception{
        return pacienteService.editarPerfil(actualizarPacienteDto);
    }

   @GetMapping("/detalle/{codigo}")
    public ResponseEntity<MensajeDto<ActualizarPacienteDto>> detalle(@PathVariable String codigo)throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false,pacienteService.obtenerPaciente(codigo)));
   }


}
