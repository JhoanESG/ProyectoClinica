package co.edu.uniquindio.proyectoclinica.model.services.interfaces;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.dto.Medico.CitasMedicoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.MedicoCrearDto;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.MedicoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.PQRSAdminDto;
import co.edu.uniquindio.proyectoclinica.model.entities.Mensaje;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;

import java.util.List;


public interface AdministradorService {

    String crearMedico(MedicoCrearDto medicoCrearDto) throws Exception;

    String actualizarMedico(MedicoDto medicoDto) throws Exception;

    String eliminarMedico(String cedula) throws Exception;

    List<MedicoListarDto> listarMedicos() throws Exception;

    MedicoDto obtenerMedico(String codigo) throws Exception;

    List<PQRSAdminDto> listarPQRS() throws Exception;

    DetallePQRSmedicoDto verDetallePQRS(int codigo) throws Exception;

    List<RespuestaDto> convertirRespuestasDto(List<Mensaje> mensajes)throws Exception;
    int responderPQRS(RespuestaPQRSDto respuestaPQRSDto) throws Exception;

    int cambiarEstadoPqrs(int codigoPqrs, EstadoPQRS estadoPQRS) throws Exception;

    List<CitasMedicoDto> listarCitasMedico () throws Exception;

}
