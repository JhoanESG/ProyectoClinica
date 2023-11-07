package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.MedicoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.paciente.*;
import co.edu.uniquindio.proyectoclinica.model.entities.*;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoUsuario;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.EmailService;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.PacienteService;
import co.edu.uniquindio.proyectoclinica.repositorios.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteServiceImp implements PacienteService {

//    @Autowired
    private final EmailServiceImp emailServiceImp;

    private final PacienteRepositorio pacienteRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final MedicoRepositorio medicoRepositorio;
    private final CitaRepo citaRepo;
    private final ConsultaRepositorio consultaRepositorio;
    private final PQRSRepo pqrsRepo;
    private final MensajeRepositorio mensajeRepositorio;

    private boolean estaRepetidoCedula(String cedula) {
        return usuarioRepositorio.findByCedula(cedula) != null;
    }

    private boolean estaRepetidoCorreo(String email, String cedula) {
        Paciente paciente = pacienteRepositorio.findByEmail(email);
        return paciente != null && !paciente.getCedula().equals(cedula);
    }

    @Override
    public String registrar(CrearPacienteDto crearPacienteDto) throws Exception {

        if (estaRepetidoCorreo(crearPacienteDto.email(), crearPacienteDto.cedula())){
            throw new Exception("El correo "+  crearPacienteDto.email()+" ya esta en uso");
        }
        if (estaRepetidoCedula(crearPacienteDto.cedula())){
            throw new Exception("La cedula "+ crearPacienteDto.cedula() +" ya esta registrada");
        }

        Paciente pacienteNuevo = new Paciente();

        pacienteNuevo.setCedula(crearPacienteDto.cedula());
        pacienteNuevo.setEmail(crearPacienteDto.email());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(crearPacienteDto.contrasena());
        pacienteNuevo.setContrasena(passwordEncriptada);

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

        if (estaRepetidoCorreo(actualizarPacienteDto.email(), actualizarPacienteDto.cedula())){
            throw new Exception("El correo "+  actualizarPacienteDto.email()+" ya esta en uso");
        }
        pacienteRepositorio.save(buscado);

        return buscado.getCedula();

    }

    @Override
    public ActualizarPacienteDto obtenerPaciente(String id) throws Exception {
        Optional<Paciente> opcional = pacienteRepositorio.findById(id);
        if (opcional.isEmpty()){
            throw new Exception("No existe un paciente con el codigo "+id);
        }
        Paciente buscado = opcional.get();

        if (buscado.getEstado() == EstadoUsuario.INACTIVO){
            throw new Exception("El usuario "+id+" se encuentra inactivo");
        }

        return new ActualizarPacienteDto(
                buscado.getCedula(),
                buscado.getNombre(),
                buscado.getApellido(),
                buscado.getTelefono(),
                buscado.getCiudad(),
                buscado.getEmail(),
                buscado.getAlergias(),
                buscado.getTipoSangre(),
                buscado.getEps(),
                buscado.getFoto()
        );
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

        EmailDto emailDto = new EmailDto(email,"Recuperar Contraseña - TuCanic","A continuacion " +
                "encontrara un link para reestablecer su contraseña");

        emailServiceImp.enviarEmail(emailDto);
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
    public DetallePQRSdto detallePQRS(int idPQRS) throws Exception {
        Optional<PQRS> opcional=  pqrsRepo.findById(idPQRS);
        if (opcional.isEmpty()){
            throw new Exception("No existe un PQRS con el codigo "+ idPQRS);
        }
        PQRS buscado = opcional.get();


        return new DetallePQRSdto(
                buscado.getId(),
                buscado.getConsulta().getId(),
                buscado.getAsunto(),
                buscado.getFechaCreacion(),
                buscado.getTipoPQRS(),
                buscado.getEstado(),
                buscado.getAsunto()
        );
    }

    @Override
    public DetalleCitaPacienteDto detalleCita(int idCita) throws Exception {
        Cita cita = citaRepo.findCitaById(idCita);

        if (cita == null){
            throw new Exception("No existe la cita con el codigo "+ idCita);
        }

        return new DetalleCitaPacienteDto(
                cita.getId(),
                cita.getFechaCita(),
                cita.getMedico().getNombre(),
                cita.getMedico().getEspecialidad(),
                cita.getFechaCreacion(),
                cita.getMotivo()

        );
    }

    @Override
    public List<PQRSpacienteDto> listarPQRSpaciente(String idPaciente) throws Exception {

        Paciente paciente =pacienteRepositorio.findByCedula(idPaciente);
        if (paciente == null){
            throw new Exception("No existe el paciente");
        }
        List<PQRS> pqrsList = pqrsRepo.findByConsultaCitaPacienteAndEstadoIn(paciente, Arrays.asList(EstadoPQRS.Activo,EstadoPQRS.Pendiente));

        List<PQRSpacienteDto> resultado = pqrsList.stream()
                .map(pqrs -> new PQRSpacienteDto(
                        pqrs.getAsunto(),
                        pqrs.getFechaCreacion(),
                        pqrs.getEstado()
                )).toList();
        return resultado;
    }

    @Override
    public List<CitaPacienteDto> listarCitasPaciente(String idPaciente) throws Exception {

        Paciente paciente =pacienteRepositorio.findByCedula(idPaciente);
        if (paciente == null){
            throw new Exception("No existe el paciente");
        }

        List<Cita> citasHoy = citaRepo.findByPacienteAndAndEstadoCita(paciente,EstadoCita.Creada);
        if (citasHoy.isEmpty()){
            throw new Exception("No hay citas");
        }

        List<CitaPacienteDto> resultado = citasHoy.stream()
                .map(cita -> new CitaPacienteDto(
                        cita.getMedico().getEspecialidad(),
                        cita.getFechaCita(),
                        cita.getEstadoCita()
                ) ).toList();

        return resultado;
    }

    @Override
    public List<ConsultaPacienteDto> listarConsultasPaciente(String idPaciente) throws Exception {

        Paciente paciente =pacienteRepositorio.findByCedula(idPaciente);
        if (paciente == null){
            throw new Exception("No existe el paciente");
        }
        List<Consulta> consultas = consultaRepositorio.findByCitaPaciente(paciente);

        if (consultas == null){
            throw new Exception("El paciente no tiene consultas");
        }

        List<ConsultaPacienteDto> resultado = consultas.stream().map(
                consulta -> new ConsultaPacienteDto(
                        consulta.getId(),
                        consulta.getCita().getFechaCita(),
                        consulta.getCita().getFechaCreacion(),
                        consulta.getCita().getMedico().getEspecialidad()
                )).toList();

        return resultado;
    }

    //Arreglar medicamentos
    @Override
    public DetalleConsultaDto detalleConsulta(int idConsulta) throws Exception {

        Consulta consulta = consultaRepositorio.findConsultaById(idConsulta);
        if (consulta== null){
            throw new Exception("No existe la consulta "+ idConsulta);
        }

        return new DetalleConsultaDto(
                consulta.getId(),
                consulta.getCita().getFechaCita(),
                consulta.getCita().getFechaCreacion(),
                consulta.getCita().getMedico().getEspecialidad(),
                consulta.getCita().getMedico().getNombre(),
                consulta.getCita().getMotivo(),
                consulta.getDiagnostico(),
                consulta.getTratamiento(),
                consulta.getTratamiento()
        ) ;
    }

    @Override
    public int responderPQRS(RespuestaPQRSDto respuestaPQRSDto) throws Exception {
        Optional<PQRS> opcional= pqrsRepo.findById(respuestaPQRSDto.codigoPqrs());
        if (opcional.isEmpty()){
            throw new Exception("No existe un PQRS con el codigo " +respuestaPQRSDto.codigoPqrs());
        }

        Optional<Usuario> opcionalUsuario= usuarioRepositorio.findById(respuestaPQRSDto.usuario());
        if (opcionalUsuario.isEmpty()){
            throw new Exception("No existe un usuario con esta cuenta "+ respuestaPQRSDto.usuario());
        }

        Mensaje mensajeNuevo= new Mensaje();
        mensajeNuevo.setPqrs(opcional.get());
        mensajeNuevo.setFecha(LocalDateTime.now());
        mensajeNuevo.setTexto(respuestaPQRSDto.descripcion());
        mensajeNuevo.setUsuario(opcionalUsuario.get());

        Mensaje respuesta = mensajeRepositorio.save(mensajeNuevo);
        return respuesta.getId();
    }

}
