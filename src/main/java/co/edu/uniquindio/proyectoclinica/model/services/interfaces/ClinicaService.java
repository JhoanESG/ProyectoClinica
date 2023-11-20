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

    List<ItemListasDto> obtenerListaCiudades()throws Exception;
    List<ItemListasDto> obtenerListaEps() throws Exception;
    List<ItemListasDto> obtenerListaTipoSangre()throws Exception;
    List<ItemListasDto> obtenerListaEspecialidades()throws Exception;
    List<ItemMedicoDto> obtenerMedicosEspecialidad(Especialidad especialidad) throws Exception;
    List<ItemListasDto> obtenerTipoPQRS()throws Exception;
}
