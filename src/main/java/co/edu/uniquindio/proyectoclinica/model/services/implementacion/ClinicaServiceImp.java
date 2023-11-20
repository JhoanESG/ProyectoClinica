package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.dto.ItemMedicoDto;
import co.edu.uniquindio.proyectoclinica.model.entities.Medico;
import co.edu.uniquindio.proyectoclinica.model.enums.Ciudad;
import co.edu.uniquindio.proyectoclinica.model.enums.Eps;
import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.enums.TipoSangre;
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

    public List<String> obtenerListaCiudades() throws Exception {
        List<String> ciudadesComoStrings = new ArrayList<>();

        for (Ciudad ciudad : Ciudad.values()) {
            ciudadesComoStrings.add(ciudad.toString());
        }

        return ciudadesComoStrings;
    }

    @Override
    public List<String> obtenerListaEps() throws Exception {
        List<String> listEps= new ArrayList<>();
        for (Eps eps1: Eps.values()){
            listEps.add(eps1.toString());
        }
        return listEps;
    }

    @Override
    public List<String> obtenerListaTipoSangre() throws Exception {
        List<String> listTipoSangre = new ArrayList<>();
        for(TipoSangre tipoSangre: TipoSangre.values()){
            listTipoSangre.add(tipoSangre.toString());
        }
        return listTipoSangre;
    }

    @Override
    public List<String> obtenerListaEspecialidades() throws Exception {
        List<String> listEspecialidades= new ArrayList<>();
        for (Especialidad especialidad: Especialidad.values()){
            listEspecialidades.add(especialidad.toString());
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
}
