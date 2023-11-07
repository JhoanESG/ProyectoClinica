package co.edu.uniquindio.proyectoclinica.model.services.interfaces;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.dto.paciente.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PacienteService {

    String registrar(CrearPacienteDto crearPacienteDto) throws Exception;

    String editarPerfil(ActualizarPacienteDto actualizarPacienteDto) throws Exception;

    ActualizarPacienteDto obtenerPaciente (String id) throws Exception;

    void eliminarCuenta(String codigoPaciente) throws Exception;

    void enviarLinkRecuperacion(String email) throws Exception;
    void confirmarCodigo(String codigo)throws Exception; //Codigo para la recuperacion de la contrasena

    void cambiarContrasena(String contrasena ,CambiarContrasenaDto cambiarContrasenaDto) throws Exception;

    void agendarCita(RegistroCitaDto registroCitaDto) throws Exception;

    void crearPQRS(CrearPQRSdto crearPQRSdto) throws Exception;

    DetallePQRSdto detallePQRS(int idPQRS) throws Exception;

    DetalleCitaPacienteDto detalleCita(int idCita) throws Exception;
    List<PQRSpacienteDto> listarPQRSpaciente(String idPaciente) throws Exception;

    List<CitaPacienteDto> listarCitasPaciente(String idPaciente) throws Exception;

    List<ConsultaPacienteDto> listarConsultasPaciente(String idPaciente)throws Exception;

    DetalleConsultaDto detalleConsulta(int idConsulta)throws Exception;
    int responderPQRS(RespuestaPQRSDto respuestaPQRSDto) throws Exception;

}
