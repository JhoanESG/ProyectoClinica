package co.edu.uniquindio.proyectoclinica.model.services;

import co.edu.uniquindio.proyectoclinica.model.dto.*;

import java.util.List;


public interface AdministradorService {

    String crearMedico(MedicoCrearDto medicoCrearDto) throws Exception;

    String actualizarMedico(MedicoCrearDto medicoCrearDto) throws Exception;

    String eliminarMedico(String cedula) throws Exception;

    List<MedicoListarDto> listarMedicos();

    void obtenerMedico();

    List<PQRSAdminDto> listarPQRS();

    String responderPQRS(RespuestaPQRSDto respuestaPQRSDto) throws Exception;

    DetallePQRS verDetallePQRS(String codigo) throws Exception;

}
