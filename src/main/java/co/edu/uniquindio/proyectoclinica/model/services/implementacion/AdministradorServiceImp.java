package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.ItemMedicamentoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.CitasMedicoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.MedicoCrearDto;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.MedicoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.PQRSAdminDto;
import co.edu.uniquindio.proyectoclinica.model.entities.*;
import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoUsuario;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.AdministradorService;
import co.edu.uniquindio.proyectoclinica.repositorios.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdministradorServiceImp implements AdministradorService {

    private final MedicoRepositorio medicoRepositorio;
    private final PQRSRepo pqrsRepo;
    private final UsuarioRepositorio usuarioRepositorio;
    private final MensajeRepositorio mensajeRepositorio;
    private final CitaRepo citaRepo;
    private final MedicamentoRepositorio medicamentoRepositorio;

    @Override
    public String crearMedico(MedicoCrearDto medicoDto) throws Exception {

        if ( estaRepetidoCorreo(medicoDto.email(), medicoDto.cedula()) ){
            throw new Exception("El correo "+ medicoDto.email() +" ya esta en uso");
        }

        if (estaRepetidoCedula(medicoDto.cedula())){
            throw new Exception("La cedula "+ medicoDto.cedula() +" ya esta registrada");
        }

        Medico medicoNuevo = new Medico();

        medicoNuevo.setCedula(medicoDto.cedula());
        medicoNuevo.setEmail(medicoDto.email());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(medicoDto.contrasena());
        medicoNuevo.setContrasena(passwordEncriptada);

        medicoNuevo.setEstado(EstadoUsuario.ACTIVO);

        medicoNuevo.setNombre(medicoDto.nombre());
        medicoNuevo.setApellido(medicoDto.apellido());
        medicoNuevo.setTelefono(medicoDto.telefono());
        medicoNuevo.setCiudad(medicoDto.ciudad());
        medicoNuevo.setFoto(medicoDto.foto());

        medicoNuevo.setEspecialidad(medicoDto.especialidad());
        medicoNuevo.setHoraInicio(medicoDto.horaInicio());
        medicoNuevo.setHoraFin(medicoDto.horaFin());


        Medico medicoRegistrado = medicoRepositorio.save(medicoNuevo);

        return medicoRegistrado.getCedula();
    }

    private boolean estaRepetidoCedula(String cedula) {
        return usuarioRepositorio.findByCedula(cedula) != null;
    }

    private boolean estaRepetidoCorreo(String email, String cedula) {
        Medico medico=  medicoRepositorio.findByEmail(email);
        return medico != null && !medico.getCedula().equals(cedula);
    }


    @Override
    public String actualizarMedico(MedicoDto medicoDto) throws Exception {

        Optional<Medico> opcional = medicoRepositorio.findById(medicoDto.cedula());
        if (opcional.isEmpty()){
            throw new Exception("No existe un medico con el codigo "+medicoDto.cedula());
        }

        Medico buscado = opcional.get();

        buscado.setCedula(medicoDto.cedula());
        buscado.setEmail(medicoDto.email());

        buscado.setNombre(medicoDto.nombre());
        buscado.setApellido(medicoDto.apellido());
        buscado.setTelefono(medicoDto.telefono());
        buscado.setCiudad(medicoDto.ciudad());
        buscado.setFoto(medicoDto.foto());

        buscado.setEspecialidad(medicoDto.especialidad());
        buscado.setHoraInicio(medicoDto.inicioJornada());
        buscado.setHoraFin(medicoDto.finJornada());

        //Buscar que no se repita la cedula y el correo del medico
        if ( estaRepetidoCorreo(medicoDto.email(), medicoDto.cedula()) ){
            throw new Exception("El correo "+ medicoDto.email() +" ya esta en uso");
        }

        medicoRepositorio.save(buscado);

        return buscado.getCedula();
    }

    @Override
    public String eliminarMedico(String cedula) throws Exception {
        Optional<Medico> opcional = medicoRepositorio.findById(cedula);
        if (opcional.isEmpty()){
            throw new Exception("No existe un medico con el codigo "+cedula);
        }
        Medico buscado = opcional.get();
        //medicoRepositorio.delete(buscado);
        buscado.setEstado(EstadoUsuario.INACTIVO);

        medicoRepositorio.save(buscado);
        return null;
    }

    @Override
    public List<MedicoListarDto> listarMedicos() throws Exception {
        List<Medico> medicos = medicoRepositorio.findAll();

        if (medicos.isEmpty()){
            throw new Exception("No hay medicos registrados");
        }

        List<MedicoListarDto> respuesta = medicos.stream().map(medico -> new MedicoListarDto(
                medico.getCedula(),
                medico.getNombre(),
                medico.getApellido(),
                medico.getEspecialidad()
        )).toList();


        return respuesta;
    }

    @Override
    public MedicoDto obtenerMedico(String cedula) throws Exception {

        Optional<Medico> opcional = medicoRepositorio.findById(cedula);
        if (opcional.isEmpty()){
            throw new Exception("No existe un medico con el codigo "+cedula);
        }
        Medico buscado = opcional.get();

        if (buscado.getEstado() == EstadoUsuario.INACTIVO){
            throw new Exception("El usuario "+cedula+" se encuentra inactivo");
        }

        return new MedicoDto(
                buscado.getCedula(),
                buscado.getNombre(),
                buscado.getApellido(),
                buscado.getTelefono(),
                buscado.getCiudad(),
                buscado.getEspecialidad(),
                buscado.getEmail(),
                buscado.getFoto(),
                buscado.getHoraInicio(),
                buscado.getHoraFin()
        );
    }

    @Override
    public List<PQRSAdminDto> listarPQRS() throws Exception {
        List<PQRS> listaPqrs= pqrsRepo.findAll();

        if (listaPqrs.isEmpty()){
            throw new Exception("No hay PQRS");
        }

        List<PQRSAdminDto> respuesta = new ArrayList<>();
        for (PQRS p: listaPqrs){
            respuesta.add(new PQRSAdminDto(
                    p.getId(),
                    p.getConsulta().getId(),
                    p.getAsunto(),
                    p.getConsulta().getCita().getPaciente().getNombre(),
                    p.getTipoPQRS(),
                    p.getEstado()
            ));

        }
        return respuesta;
    }

    @Override
    public DetallePQRSmedicoDto verDetallePQRS(int codigo) throws Exception {

        Optional<PQRS> opcional=  pqrsRepo.findById(codigo);
        if (opcional.isEmpty()){
            throw new Exception("No existe un PQRS con el codigo "+ codigo);
        }
        PQRS buscado = opcional.get();

        // Separar fecha con hora
        return new DetallePQRSmedicoDto(
                buscado.getId(),
                buscado.getConsulta().getCita().getPaciente().getCedula(),
                buscado.getAsunto(),
                buscado.getEstado(),
                buscado.getTipoPQRS(),
                buscado.getFechaCreacion(),
                buscado.getFechaCreacion(),
                buscado.getConsulta().getCita().getMedico().getNombre(),
                buscado.getConsulta().getCita().getMedico().getEspecialidad(),
                buscado.getFechaCreacion(),
                buscado.getDescripcion()
                );
    }

    @Override
    public List<RespuestaDto> convertirRespuestasDto(List<Mensaje> mensajes) throws Exception {
        return mensajes.stream().map( m -> new RespuestaDto(
                m.getId(),
                m.getTexto(),
                m.getUsuario().getCedula(),
                m.getFecha()
        )).toList();
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


    @Override
    public int cambiarEstadoPqrs(int codigoPqrs, EstadoPQRS estadoPQRS) throws Exception {
         Optional<PQRS> opcional =pqrsRepo.findById(codigoPqrs);
         if (opcional.isEmpty()){
             throw new Exception("No existe un PQRS con el codigo "+ codigoPqrs);
         }
         PQRS pqrs = opcional.get();
         pqrs.setEstado(estadoPQRS);
         pqrsRepo.save(pqrs);
         return pqrs.getId();
    }

    @Override
    public List<CitasMedicoDto> listarCitasMedico() throws Exception {
        List<Cita> citas = citaRepo.findAll();
        List<CitasMedicoDto> respuesta= new ArrayList<>();

        if (citas.isEmpty()){
            throw new Exception("No existen citas creadas");
        }

        for(Cita c : citas){
            respuesta.add(new CitasMedicoDto(
                    c.getId(),
                    c.getPaciente().getNombre(),
                    c.getPaciente().getCedula(),
                    c.getFechaCita(),
                    c.getMotivo()
            ));
        }
        return respuesta;
    }

    @Override
    public int crearMedicamento(MedicamentoDto medicamentoCrearDto) throws Exception {
        Medicamento medicamento= new Medicamento();
        medicamento.setNombre(medicamentoCrearDto.nombre());
        medicamento.setPosologia(medicamentoCrearDto.posologia());
        medicamentoRepositorio.save(medicamento);

        return medicamento.getId();
    }

    @Override
    public int actualizarMedicamento(ItemMedicamentoDto itemMedicamentoDto) throws Exception {
        Medicamento buscado = medicamentoRepositorio.findById(itemMedicamentoDto.codigo());
        if (buscado== null){
            throw new Exception("No existe un medicamento con el codigo "+itemMedicamentoDto.codigo());
        }

        buscado.setNombre(itemMedicamentoDto.nombre());
        buscado.setPosologia(itemMedicamentoDto.posologia());

        return buscado.getId();
    }

    @Override
    public int eliminarMedicamento(int codigo) throws Exception {
        Medicamento buscado = medicamentoRepositorio.findById(codigo);
        if (buscado== null){
            throw new Exception("No existe un medicamento con el codigo "+codigo);
        }

        medicamentoRepositorio.delete(buscado);

        return 0;
    }

    @Override
    public MedicamentoDto obtenerMedicamento(int codigo) throws Exception {
        Medicamento buscado = medicamentoRepositorio.findById(codigo);
        if (buscado== null){
            throw new Exception("No existe un medicamento con el codigo "+codigo);
        }

        return new MedicamentoDto(buscado.getNombre(), buscado.getPosologia());
    }

    @Override
    public List<ItemMedicamentoDto> listarMedicamentos() throws Exception {
        List<Medicamento> medicamentos= medicamentoRepositorio.findAll();
        if (medicamentos.isEmpty()){
            throw new Exception("No hay medicamentos");
        }
        List<ItemMedicamentoDto> respuesta = medicamentos.stream().map(medicamento -> new ItemMedicamentoDto(
                medicamento.getId(),
                medicamento.getNombre(),
                medicamento.getPosologia()
        )).toList();

        return respuesta;

    }
}
