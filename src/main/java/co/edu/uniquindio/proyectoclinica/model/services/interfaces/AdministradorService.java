package co.edu.uniquindio.proyectoclinica.model.services.interfaces;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;

import java.util.List;


public interface AdministradorService {

    String crearMedico(MedicoCrearDto medicoCrearDto) throws Exception;

    String actualizarMedico(MedicoCrearDto medicoCrearDto) throws Exception;

    String eliminarMedico(String cedula) throws Exception;

    List<MedicoListarDto> listarMedicos() throws Exception;

    MedicoDto obtenerMedico(String codigo) throws Exception;

    List<PQRSAdminDto> listarPQRS() throws Exception;

    String responderPQRS(RespuestaPQRSDto respuestaPQRSDto) throws Exception;

    DetallePQRSdto verDetallePQRS(Integer codigo) throws Exception;

    void cambiarEstadoPqrs(int codigoPqrs, EstadoPQRS estadoPQRS) throws Exception;

    List<CitasMedicoDto> listarCitasMedico () throws Exception;

}
