package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.dto.paciente.ActualizarPacienteDto;
import co.edu.uniquindio.proyectoclinica.model.dto.paciente.CrearPQRSdto;
import co.edu.uniquindio.proyectoclinica.model.dto.paciente.CrearPacienteDto;
import co.edu.uniquindio.proyectoclinica.model.entities.*;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoUsuario;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.PacienteService;
import co.edu.uniquindio.proyectoclinica.repositorios.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteServiceImp implements PacienteService {

    private final PacienteRepositorio pacienteRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final MedicoRepositorio medicoRepositorio;
    private final CitaRepo citaRepo;
    private final ConsultaRepositorio consultaRepositorio;
    private final PQRSRepo pqrsRepo;

    private boolean estaRepetidoCedula(String cedula) {
        return usuarioRepositorio.findByCedula(cedula) != null;
    }

    private boolean estaRepetidoCorreo(String email) {
        return usuarioRepositorio.findByEmail(email) != null;
    }

    @Override
    public String registrar(CrearPacienteDto crearPacienteDto) throws Exception {

        if (estaRepetidoCorreo(crearPacienteDto.email())){
            throw new Exception("El correo "+  crearPacienteDto.email()+" ya esta en uso");
        }
        if (estaRepetidoCedula(crearPacienteDto.cedula())){
            throw new Exception("La cedula "+ crearPacienteDto.cedula() +" ya esta registrada");
        }

        Paciente pacienteNuevo = new Paciente();

        pacienteNuevo.setCedula(crearPacienteDto.cedula());
        pacienteNuevo.setEmail(crearPacienteDto.email());
        pacienteNuevo.setContrasena(crearPacienteDto.contrasena());
        pacienteNuevo.setEstado(EstadoUsuario.ACTIVO);

        pacienteNuevo.setNombre(crearPacienteDto.nombre());
        pacienteNuevo.setApellido(crearPacienteDto.apellido());
        pacienteNuevo.setTelefono(crearPacienteDto.telefono());
        pacienteNuevo.setCiudad(crearPacienteDto.ciudad());
        pacienteNuevo.setFoto(crearPacienteDto.foto());

        pacienteNuevo.setFechaNacimiento(crearPacienteDto.fechaNacimiento());
        pacienteNuevo.setAlergias(crearPacienteDto.alergias());
        pacienteNuevo.setEps(crearPacienteDto.eps());
        pacienteNuevo.setTipoSangre(crearPacienteDto.tipoSangre());

        Paciente pacienteRegistrado = pacienteRepositorio.save(pacienteNuevo);

        return pacienteRegistrado.getCedula();
    }

    @Override
    public String editarPerfil(ActualizarPacienteDto actualizarPacienteDto) throws Exception {

        Optional<Paciente> opcional= pacienteRepositorio.findById(actualizarPacienteDto.cedula());
        if (opcional.isEmpty()){
            throw new Exception("No existe un paciente con el codigo "+ actualizarPacienteDto.cedula());
        }
        Paciente buscado = opcional.get();

        buscado.setEmail(actualizarPacienteDto.email());

        buscado.setNombre(actualizarPacienteDto.nombre());
        buscado.setApellido(actualizarPacienteDto.apellido());
        buscado.setTelefono(actualizarPacienteDto.telefono());
        buscado.setCiudad(actualizarPacienteDto.ciudad());
        buscado.setFoto(actualizarPacienteDto.foto());

        buscado.setAlergias(actualizarPacienteDto.alergias());
        buscado.setTipoSangre(actualizarPacienteDto.tipoSangre());
        buscado.setEps(actualizarPacienteDto.eps());
        buscado.setFechaNacimiento(actualizarPacienteDto.fechaNacimiento());

        if (estaRepetidoCorreo(buscado.getEmail())){
            throw new Exception("El correo "+  buscado.getEmail()+" ya esta en uso");
        }
        pacienteRepositorio.save(buscado);

        return buscado.getCedula();

    }

    @Override
    public void eliminarCuenta(String codigoPaciente) throws Exception {
        Optional<Paciente> opcional = pacienteRepositorio.findById(codigoPaciente);
        if (opcional.isEmpty()){
            throw new Exception("No existe un paciente con el codigo "+codigoPaciente);
        }
        Paciente buscado = opcional.get();
        //medicoRepositorio.delete(buscado);
        buscado.setEstado(EstadoUsuario.INACTIVO);

        pacienteRepositorio.save(buscado);

    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

    }

    @Override
    public void confirmarCodigo(String codigo) throws Exception {

    }

    @Override
    public void cambiarContrasena(String contrasena,CambiarContrasenaDto cambiarContrasenaDto) throws Exception {
        Optional<Paciente> buscado = pacienteRepositorio.findById(cambiarContrasenaDto.cc());
        if (buscado.isEmpty()){
            throw new Exception("No se encontró el usuario");
        }
        Paciente paciente = buscado.get();
        paciente.setContrasena(contrasena);
        pacienteRepositorio.save(paciente);
    }


    @Override
    public void agendarCita(RegistroCitaDto registroCitaDto) throws Exception {

        Cita citaNuevo = new Cita();

        citaNuevo.setFechaCreacion(LocalDateTime.now());
        citaNuevo.setFechaCita(registroCitaDto.fechaCita());
        citaNuevo.setMotivo(registroCitaDto.motivo());
        citaNuevo.setEstadoCita(EstadoCita.Creada);

        Paciente paciente = pacienteRepositorio.findByCedula(registroCitaDto.ccPaciente());
        Medico medico = medicoRepositorio.findByCedula(registroCitaDto.ccMedico());

        if (paciente == null) {
            throw new Exception("Paciente no encontrado");
        }
        if (medico == null) {
            throw new Exception("Médico no encontrado");
        }
        citaNuevo.setPaciente(paciente);
        citaNuevo.setMedico(medico);
        citaRepo.save(citaNuevo);
    }

    @Override
    public void crearPQRS(CrearPQRSdto crearPQRSdto) throws Exception {
        PQRS pqrs = new PQRS();

        Consulta consulta = consultaRepositorio.findConsultaById(crearPQRSdto.idConsulta());

        if (consulta == null) {
            throw new Exception("consulta no encontrada");
        }
        pqrs.setConsulta(consulta);
        pqrs.setFechaCreacion(LocalDateTime.now());
        pqrs.setEstado(EstadoPQRS.Activo);
        pqrs.setTipoPQRS(crearPQRSdto.tipoPQRS());
        pqrs.setAsunto(crearPQRSdto.asunto());
        pqrs.setDescripcion(crearPQRSdto.descripcion());

        pqrsRepo.save(pqrs);

    }

    @Override
    public void listarPQRSpaciente() throws Exception {

    }

    @Override
    public void responderPQRS() throws Exception {

    }

    @Override
    public void filtarCitasPorFecha() throws Exception {

    }

    @Override
    public void filtrarCitasPorFecha() throws Exception {

    }
}
