package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.enums.Ciudad;
import co.edu.uniquindio.proyectoclinica.model.enums.Eps;
import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.enums.TipoSangre;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.ClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClinicaServiceImp implements ClinicaService {
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
            listTipoSangre.add(tipoSangre.getNombre());
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
}
