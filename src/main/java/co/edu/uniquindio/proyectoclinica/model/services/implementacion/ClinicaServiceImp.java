package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.dto.ItemListasDto;
import co.edu.uniquindio.proyectoclinica.model.dto.ItemMedicoDto;
import co.edu.uniquindio.proyectoclinica.model.entities.Medico;
import co.edu.uniquindio.proyectoclinica.model.enums.*;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.ClinicaService;
import co.edu.uniquindio.proyectoclinica.repositorios.MedicoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClinicaServiceImp implements ClinicaService {

    private final MedicoRepositorio medicoRepositorio;

    public List<ItemListasDto> obtenerListaCiudades() throws Exception {
        List<ItemListasDto> ciudadesComoStrings = new ArrayList<>();

        for (Ciudad ciudad : Ciudad.values()) {
            ciudadesComoStrings.add(new ItemListasDto(ciudad.toString()));
        }

        return ciudadesComoStrings;
    }

    @Override
    public List<ItemListasDto> obtenerListaEps() throws Exception {
        List<ItemListasDto> listEps= new ArrayList<>();
        for (Eps eps1: Eps.values()){
            listEps.add(new ItemListasDto(eps1.toString()));
        }
        return listEps;
    }

    @Override
    public List<ItemListasDto> obtenerListaTipoSangre() throws Exception {
        List<ItemListasDto> listTipoSangre = new ArrayList<>();
        for(TipoSangre tipoSangre: TipoSangre.values()){
            listTipoSangre.add(new ItemListasDto(tipoSangre.toString()));
        }
        return listTipoSangre;
    }

    @Override
    public List<ItemListasDto> obtenerListaEspecialidades() throws Exception {
        List<ItemListasDto> listEspecialidades= new ArrayList<>();
        for (Especialidad especialidad: Especialidad.values()){
            listEspecialidades.add(new ItemListasDto( especialidad.toString()));
        }
        return listEspecialidades;
    }

    @Override
    public List<ItemMedicoDto> obtenerMedicosEspecialidad(Especialidad especialidad) throws Exception {
        List<Medico> medicos = medicoRepositorio.findByEspecialidad(especialidad);

        if (medicos.isEmpty()){
            throw new Exception("No hay medicos registrados");
        }
        List<ItemMedicoDto> respuesta = medicos.stream().map(medico -> new ItemMedicoDto(
                medico.getNombre()+" "+medico.getApellido(),
                medico.getCedula()
        )).toList();

        return respuesta;
    }

    @Override
    public List<ItemListasDto> obtenerTipoPQRS() throws Exception {
        List<ItemListasDto> listPQRS= new ArrayList<>();
        for (TipoPQRS pqrs: TipoPQRS.values()){
            listPQRS.add(new ItemListasDto( pqrs.toString()));
        }
        return listPQRS;
    }
}
