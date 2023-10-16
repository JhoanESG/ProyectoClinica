package co.edu.uniquindio.proyectoclinica.model.services.interfaces;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.dto.paciente.CrearPacienteDto;

public interface PacienteService {

    String registrar(CrearPacienteDto crearPacienteDto) throws Exception;

    String editarPerfil(ActualizarPacienteDto actualizarPacienteDto) throws Exception;

    void eliminarCuenta(String codigoPaciente) throws Exception;

    void enviarLinkRecuperacion(String email) throws Exception;
    void confirmarCodigo(String codigo)throws Exception; //Codigo para la recuperacion de la contrasena

    void cambiarContrasena(String contrasena ,CambiarContrasenaDto cambiarContrasenaDto) throws Exception;

    void agendarCita(RegistroCitaDto registroCitaDto) throws Exception;

    void crearPQRS() throws Exception;

    void listarPQRSpaciente() throws Exception;

    void responderPQRS() throws Exception;

    //Los metodos filtrar se pueden tener como solo uno que se interpreta con distintos parametros
    void filtarCitasPorFecha() throws Exception;

    void filtrarCitasPorFecha() throws Exception;
}
