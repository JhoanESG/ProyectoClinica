package co.edu.uniquindio.proyectoclinica.model.services.interfaces;

import co.edu.uniquindio.proyectoclinica.model.dto.ItemListasDto;
import co.edu.uniquindio.proyectoclinica.model.dto.ItemMedicoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.MedicoListarDto;
import co.edu.uniquindio.proyectoclinica.model.enums.Ciudad;
import co.edu.uniquindio.proyectoclinica.model.enums.Eps;
import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.enums.TipoSangre;

import java.util.List;

public interface ClinicaService {

    List<String> obtenerListaCiudades()throws Exception;
    List<String> obtenerListaEps() throws Exception;
    List<String> obtenerListaTipoSangre()throws Exception;
    List<String> obtenerListaEspecialidades()throws Exception;
    List<ItemMedicoDto> obtenerMedicosEspecialidad(String especialidad) throws Exception;
    List<String> obtenerTipoPQRS()throws Exception;
    List<String> obtenerEstadoDia()throws Exception;
}
