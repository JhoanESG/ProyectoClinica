package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.entities.Medico;
import co.edu.uniquindio.proyectoclinica.model.entities.Mensaje;
import co.edu.uniquindio.proyectoclinica.model.entities.PQRS;
import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoUsuario;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.AdministradorService;
import co.edu.uniquindio.proyectoclinica.repositorios.CuentaRepositorio;
import co.edu.uniquindio.proyectoclinica.repositorios.MedicoRepositorio;
import co.edu.uniquindio.proyectoclinica.repositorios.MensajeRepositorio;
import co.edu.uniquindio.proyectoclinica.repositorios.PQRSRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorServiceImp implements AdministradorService {

    private final MedicoRepositorio medicoRepositorio;
    private final PQRSRepo pqrsRepo;
    private final CuentaRepositorio cuentaRepositorio;
    private final MensajeRepositorio mensajeRepositorio;

    @Override
    public String crearMedico(MedicoCrearDto medicoDto) throws Exception {

        if ( estaRepetidoCorreo(medicoDto.email()) ){
            throw new Exception("EL correo "+ medicoDto.email() +" ya esta en uso");
        }

        if (estaRepetidoCedula(medicoDto.cedula())){
            throw new Exception("La cedula "+ medicoDto.cedula() +" ya esta registrada");
        }

        Medico medicoNuevo = new Medico();

        medicoNuevo.setCedula(medicoDto.cedula());
        medicoNuevo.setEmail(medicoDto.email());
        medicoNuevo.setContrasena(medicoDto.contrasena());
        medicoNuevo.setEstado(EstadoUsuario.ACTIVO);

        medicoNuevo.setNombre(medicoDto.nombre());
        medicoNuevo.setApellido(medicoDto.apellido());
        medicoNuevo.setTelefono(medicoDto.telefono());
        medicoNuevo.setCiudad(medicoDto.ciudad());
        medicoNuevo.setFoto(medicoDto.foto());

        medicoNuevo.setEspecialidad(Especialidad.values()[medicoDto.especialidad()]);
        medicoNuevo.setHoraInicio(medicoDto.horaInicio());
        medicoNuevo.setHoraFin(medicoDto.horaFin());


        Medico medicoRegistrado = medicoRepositorio.save(medicoNuevo);

        return medicoRegistrado.getCedula();
    }

    private boolean estaRepetidoCedula(String cedula) {
        return medicoRepositorio.findByCedula(cedula) != null;
    }

    private boolean estaRepetidoCorreo(String email) {
        return medicoRepositorio.findByEmail(email) != null;
    }



    @Override
    public String actualizarMedico(MedicoCrearDto medicoDto) throws Exception {

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

        buscado.setEspecialidad(Especialidad.values()[medicoDto.especialidad()]);
        buscado.setHoraInicio(medicoDto.horaInicio());
        buscado.setHoraFin(medicoDto.horaFin());

        //Buscar que no se repita la cedula y el correo del medico

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

        return new MedicoDto(
                buscado.getCedula(),
                buscado.getNombre(),
                buscado.getApellido(),
                buscado.getTelefono(),
                buscado.getCiudad(),
                buscado.getEspecialidad(),
                buscado.getHoraInicio(),
                buscado.getHoraFin()
        );
    }

    @Override
    public List<PQRSAdminDto> listarPQRS() throws Exception {
        List<PQRS> listaPqrs= pqrsRepo.findAll();

        List<PQRSAdminDto> respuesta = new ArrayList<>();
        for (PQRS p: listaPqrs){
            respuesta.add(new PQRSAdminDto(
                    p.getId(),
                    p.getConsulta().getId(),
                    p.getAsunto(),
                    p.getConsulta().getCita().getPaciente().getNombre(),
                    p.getEstado()
            ));
            return respuesta;
        }

        return null;
    }

    @Override
    public String responderPQRS(RespuestaPQRSDto respuestaPQRSDto) throws Exception {
        Optional<PQRS> opcional= pqrsRepo.findById(respuestaPQRSDto.codigo());
        if (opcional.isEmpty()){
            throw new Exception("No existe este pqrs");
        }

        Optional<PQRS> opcionalUsuario= pqrsRepo.findById(respuestaPQRSDto.usuario());
        if (opcionalUsuario.isEmpty()){
            throw new Exception("No existe este ");
        }

        Mensaje mensajeNuevo= new Mensaje();
        mensajeNuevo.setPqrs(opcional.get());
        mensajeNuevo.setFecha(LocalDateTime.now());
        mensajeNuevo.setTexto(respuestaPQRSDto.descripcion());

        return null;
    }

    @Override
    public DetallePQRSdto verDetallePQRS(Integer codigo) throws Exception {

        Optional<PQRS> opcional=  pqrsRepo.findById(codigo);
        if (opcional.isEmpty()){
            throw new Exception("No existe un PQRs con el codigo "+ codigo);
        }
        PQRS buscado = opcional.get();

        return new DetallePQRSdto(
                buscado.getId(),
                buscado.getEstado());
    }
}
