package co.edu.uniquindio.proyectoclinica.controllers;

import co.edu.uniquindio.proyectoclinica.model.dto.ItemListasDto;
import co.edu.uniquindio.proyectoclinica.model.dto.ItemMedicoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.MensajeDto;
import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.ClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/listar-medicos/{especialidad}")
    public ResponseEntity<MensajeDto<List<ItemMedicoDto>>> listarMedicos(@PathVariable String especialidad)throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false, clinicaService.obtenerMedicosEspecialidad(especialidad)));
    }

    @GetMapping("/listar-tipoPQRS")
    public ResponseEntity<MensajeDto<List<String>>> listarTipoPQRS() throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false, clinicaService.obtenerTipoPQRS()));
    }

    @GetMapping("/listar-estadoDia")
    public ResponseEntity<MensajeDto<List<String>>> listarEstadoDia() throws Exception{
        return ResponseEntity.ok().body(new MensajeDto<>(false, clinicaService.obtenerEstadoDia()));
    }

}
