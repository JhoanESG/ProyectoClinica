package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.entities.Medico;
import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.AdministradorService;
import co.edu.uniquindio.proyectoclinica.repositorios.MedicoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministradorServiceImp implements AdministradorService {

    private final MedicoRepositorio medicoRepositorio;

    @Override
    public String crearMedico(MedicoCrearDto medicoDto) throws Exception {
        Medico medicoNuevo = new Medico();

        medicoNuevo.setCedula(medicoDto.cedula());
        medicoNuevo.setEmail(medicoDto.email());
        medicoNuevo.setContrasena(medicoDto.contrasena());

        medicoNuevo.setNombre(medicoDto.nombre());
        medicoNuevo.setApellido(medicoDto.apellido());
        medicoNuevo.setTelefono(medicoDto.telefono());
        medicoNuevo.setCiudad(medicoDto.ciudad());
        medicoNuevo.setFoto(medicoDto.foto());

        medicoNuevo.setEspecialidad(Especialidad.values()[medicoDto.especialidad()]);
        medicoNuevo.setHoraInicio(medicoDto.horaInicio());
        medicoNuevo.setHoraFin(medicoDto.horaFin());

        if ( estaRepetidoCorreo(medicoDto.email()) ){
            throw new Exception("EL correo ya esta en uso");
        }

        if (estaRepetidoCedula(medicoDto.cedula())){
            throw new Exception("La cedula ya esta registrada");
        }

        Medico medicoRegistrado = medicoRepositorio.save(medicoNuevo);

        return medicoRegistrado.getCedula();
    }

    private boolean estaRepetidoCedula(String cedula) {
        medicoRepositorio.buscarPorCedula(cedula);
    }

    private boolean estaRepetidoCorreo(String email) {
        medicoRepositorio.buscarPorCorreo(email);
    }



    @Override
    public String actualizarMedico(MedicoCrearDto medicoCrearDto) throws Exception {
        return null;
    }

    @Override
    public String eliminarMedico(String cedula) throws Exception {
        return null;
    }

    @Override
    public List<MedicoListarDto> listarMedicos() throws Exception {
        return null;
    }

    @Override
    public MedicoDto obtenerMedico(String codigo) throws Exception {
        return null;
    }

    @Override
    public List<PQRSAdminDto> listarPQRS() throws Exception {
        return null;
    }

    @Override
    public String responderPQRS(RespuestaPQRSDto respuestaPQRSDto) throws Exception {
        return null;
    }

    @Override
    public DetallePQRS verDetallePQRS(String codigo) throws Exception {
        return null;
    }
}
