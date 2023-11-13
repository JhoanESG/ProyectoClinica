package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.*;
import co.edu.uniquindio.proyectoclinica.model.dto.paciente.DetalleConsultaDto;
import co.edu.uniquindio.proyectoclinica.model.entities.*;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoDiaLibre;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.MedicoService;
import co.edu.uniquindio.proyectoclinica.repositorios.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MedicoServiceImp implements MedicoService {

    private final MedicoRepositorio medicoRepositorio;
    private final CitaRepo citaRepo;
    private final MedicamentoRepositorio medicamentoRepositorio;
    private final ConsultaRepositorio consultaRepositorio;
    private final DiaLibreRepositorio diaLibreRepositorio;

    //TODO Listar las citas pendientes por fecha
    // listar citas con estado terminadas para medico.

    //Citas del de hoy
    @Override
    public List<CitaMedicoDTo> verCitasPendientes(String codigoMedico) throws Exception {
        Medico medico = medicoRepositorio.findByCedula(codigoMedico);

        if (medico == null){
            throw new Exception("no existe ningun medico con el codigo "+codigoMedico);
        }

        List<Cita> citasHoy = citaRepo.findByMedicoAndEstadoCita(medico,EstadoCita.Creada);

        if (citasHoy.isEmpty()){
            throw new Exception("No hay citas para hoy");
        }
        List<CitaMedicoDTo> resultado = citasHoy.stream().map(
                cita -> new CitaMedicoDTo(
                        cita.getPaciente().getNombre(),
                        cita.getEstadoCita(),
                        cita.getFechaCita()
                )).toList();
        return resultado;
    }

    //Citas pendientes
    @Override
    public List<CitasMedicoDto> listarCitasMedico(String codigoMedico) throws Exception {

        Medico medico = medicoRepositorio.findByCedula(codigoMedico);

        if (medico == null){
            throw new Exception("no existe ningun medico con el codigo "+codigoMedico);
        }

        LocalDateTime fechaHoy = LocalDateTime.now();
        List<Cita> citasHoy = citaRepo.findByFechaCitaAfterAndEstadoCita(fechaHoy,EstadoCita.Creada);

        List<CitasMedicoDto> resultado = citasHoy.stream().map(
                cita -> new CitasMedicoDto(
                        cita.getPaciente().getNombre(),
                        cita.getPaciente().getCedula(),
                        cita.getFechaCita(),
                        cita.getMotivo()
                )).toList();

        return resultado;
    }



    @Override
    public DetalleCitaDto obtenerCita(int codigoCita) throws Exception {

        Cita cita = citaRepo.findCitaById(codigoCita);
        if (cita == null){
            throw new Exception("No hay citas con ese codigo "+codigoCita);
        }

        return new DetalleCitaDto(
                cita.getPaciente().getNombre(),
                cita.getPaciente().getApellido(),
                cita.getPaciente().getApellido(),
                cita.getPaciente().getAlergias(),
                cita.getPaciente().getTipoSangre(),
                cita.getPaciente().getEps(),
                cita.getFechaCita(),
                cita.getMotivo()
        );
    }

    @Override
    public DetalleConsultaMedicoDto detalleConsulta(int codigoCita) throws Exception {
        Consulta consulta = consultaRepositorio.findConsultaByCitaId(codigoCita);
        if (consulta== null){
            throw new Exception("No hay consultas relacionadas con el codigo de la cita "+ codigoCita);
        }
        List<MedicamentosDto> prueba = new ArrayList<>();
        prueba.add(new MedicamentosDto("Prueba","2"));

        return new DetalleConsultaMedicoDto(
                consulta.getCita().getPaciente().getNombre(),
                consulta.getCita().getPaciente().getApellido(),
                consulta.getCita().getPaciente().getCiudad(),
                consulta.getCita().getPaciente().getTelefono(),
                consulta.getCita().getPaciente().getTipoSangre(),
                consulta.getCita().getPaciente().getAlergias(),
                consulta.getCita().getPaciente().getEps(),
                consulta.getSintomas(),
                consulta.getDiagnostico(),
                consulta.getTratamiento(),
                consulta.getNotas(),
                prueba
        );
    }

    @Override
    public int radicarConsulta (AtenderCitaDto atenderCitaDto) throws Exception {
        Cita cita = citaRepo.findCitaById(atenderCitaDto.idCita());
        if (cita == null){
            throw new Exception("No existe la cita");
        }
        LocalDateTime fechaActual = LocalDateTime.now();
        LocalDateTime fechaCita = cita.getFechaCita();

        if (!fechaCita.toLocalDate().isEqual(fechaActual.toLocalDate())){
            throw new Exception("La fecha actual no coincide con la fecha de la cita");
        }else if (!(fechaActual.isAfter(fechaCita))){
            throw new Exception("La hora actual no es posterior a la hora de la cita");
        }

        Consulta consulta = new Consulta();

        List<ConsultaMedicamento> consultaMedicamentos= new ArrayList<>();
        cita.setConsulta(consulta);
        consulta.setSintomas(atenderCitaDto.sintomas());
        consulta.setDiagnostico(atenderCitaDto.diagnostico());
        consulta.setTratamiento(atenderCitaDto.tratamiento());
        consulta.setNotas(atenderCitaDto.notas());
        //consulta.setConsultaMedicamentos(atenderCitaDto.medicamentos());

        consultaRepositorio.save(consulta);

        cita.setEstadoCita(EstadoCita.terminado);
        citaRepo.save(cita);

        return consulta.getId();
    }

    public void asignarMedicamentosCita (Cita cita, List<MedicamentosDto> medicamentos)throws Exception{
        for (MedicamentosDto m: medicamentos){
            //Medicamento medicamento= medicamentoRepositorio.findById(MedicamentosDto.)
        }
    }

    //Historial de citas atendidas
    @Override
    public List<HistorialPacientesAtendidosDto> listarCitasAtendidas(String idMedico) throws Exception {
        Medico medico = medicoRepositorio.findByCedula(idMedico);
        if (medico==null){
            throw new Exception("No existe un medico con el codigo "+idMedico);
        }

        List<Cita> citasAtendidas = citaRepo.findByMedicoAndEstadoCita(medico, EstadoCita.terminado);
        List<HistorialPacientesAtendidosDto> historialPacientesAtendidosDtos= new ArrayList<>();

        for (Cita c: citasAtendidas){
            historialPacientesAtendidosDtos.add(
                    new HistorialPacientesAtendidosDto(
                            c.getPaciente().getNombre(),  // Nombre del paciente
                            c.getPaciente().getCedula(),  // ID del paciente
                            c.getFechaCita(),            // Fecha y hora de la cita
                            c.getMotivo()                // Motivo de la cita
                    ));
        }

        return historialPacientesAtendidosDtos;
    }

    @Override
    public int asignarDiaLibre(DiaLibreDto diaLibreDto) throws Exception {
        Medico medico = medicoRepositorio.findByCedula(diaLibreDto.idMedico());
        if (medico== null){
            throw new Exception("No existe un medico con el codigo "+ diaLibreDto.idMedico());
        }
        // Verificar si el médico tiene citas programadas en el día seleccionado

        List<Cita> citasEnDia = citaRepo.findByMedicoAndFechaCitaBetween(medico, diaLibreDto.fecha().atStartOfDay(),diaLibreDto.fecha().atStartOfDay().withHour(23).withMinute(59));
        if (!citasEnDia.isEmpty()) {
            throw new Exception("El médico tiene citas programadas en el día seleccionado. No se puede asignar un día libre.");
        }
        List<DiaLibre> diasLibres= diaLibreRepositorio.findByMedicoAndEstadoDiaLibre(medico, EstadoDiaLibre.ACTIVO);
        if (diasLibres.size()>=1){
            throw new Exception("Solo se puede tener un dia libre activo a la vez");
        }
        DiaLibre diaLibre = new DiaLibre();
        diaLibre.setDia(diaLibreDto.fecha());
        diaLibre.setMedico(medico);

        // Guardar el día libre en la entidad DiaLibre
        diaLibreRepositorio.save(diaLibre);
        return diaLibre.getId();
    }

    @Override
    public List<DiaLibreDto> listarDiasLibresMedico(String idMedico) throws Exception {

        Medico medico = medicoRepositorio.findByCedula(idMedico);
        if (medico== null){
            throw new Exception("No existe un medico con el codigo "+ idMedico);
        }
        List<DiaLibre> diaLibres= diaLibreRepositorio.findByMedicoAndEstadoDiaLibre(medico,EstadoDiaLibre.ACTIVO);

        List<DiaLibreDto> resultado= diaLibres.stream().map(
                diaLibre -> new DiaLibreDto(
                        diaLibre.getDia(),
                        diaLibre.getMedico().getCedula()
                )).toList();

        return resultado;
    }

    @Override
    public String cambiarEstadoDiaLibre(int diaLibre, EstadoDiaLibre estadoDiaLibre) throws Exception {
        return null;
    }


}
