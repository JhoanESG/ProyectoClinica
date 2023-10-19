package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.*;
import co.edu.uniquindio.proyectoclinica.model.entities.*;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;
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


    @Override
    public List<CitaMedicoDTo> verCitasPendientes(String codigoMedico) throws Exception {
        Medico medico = medicoRepositorio.findByCedula(codigoMedico);

        if (medico == null){
            throw new Exception("no existe ningun medico con el codigo "+codigoMedico);
        }
        List<Cita> citasHoy = medico.getListaCitas().stream().filter(
                cita -> {
                    LocalDate fechaCita = cita.getFechaCita().toLocalDate();
                    return fechaCita.equals(LocalDate.now()) && cita.getEstadoCita() == EstadoCita.Creada;
                }
        ).toList();

        if (citasHoy.isEmpty()){
            throw new Exception("No hay citas para hoy");
        }
        List<CitaMedicoDTo> resultado = citasHoy.stream().map(
                cita -> new CitaMedicoDTo(
                        cita.getPaciente().getNombre(), // Puedes obtener el nombre del paciente desde la relación
                        cita.getEstadoCita(),
                        cita.getFechaCita()
                )).toList();
        return resultado;
    }

    @Override
    public List<CitasMedicoDto> listarCitasMedico(String codigoMedico) throws Exception {

        Medico medico = medicoRepositorio.findByCedula(codigoMedico);

        if (medico == null){
            throw new Exception("no existe ningun medico con el codigo "+codigoMedico);
        }

        LocalDate fechaHoy = LocalDate.now();
        List<Cita> citasHoy = medico.getListaCitas().stream().filter(
                cita -> {
                    LocalDate fechaCita = cita.getFechaCita().toLocalDate();
                    return fechaCita.equals(fechaHoy) || fechaCita.isAfter(fechaHoy) && cita.getEstadoCita() == EstadoCita.Creada;
                }
        ).toList();

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
    public void radicarConsulta (AtenderCitaDto atenderCitaDto) throws Exception {
        Cita cita = citaRepo.findCitaById(atenderCitaDto.idCita());
        if (cita == null){
            throw new Exception("No existe la cita");
        }

        Consulta consulta = new Consulta();

        List<ConsultaMedicamento> consultaMedicamentos= new ArrayList<>();
        cita.setConsulta(consulta);
        consulta.setSintomas(atenderCitaDto.sintomas());
        consulta.setDiagnostico(atenderCitaDto.diagnostico());
        consulta.setTratamiento(atenderCitaDto.tratamiento());
        consulta.setNotas(atenderCitaDto.notas());
        //asignarMedicamentosCita(cita,atenderCitaDto.medicamentos());
        consultaRepositorio.save(consulta);

    }

    private void asignarMedicamentosCita (Cita cita, List<MedicamentosDto> medicamentos)throws Exception{
        for (MedicamentosDto m: medicamentos){
            //Medicamento medicamento= medicamentoRepositorio.findById(MedicamentosDto.)
        }
    }


    @Override
    public List<HistorialPacientesAtendidosDto> listarCitasAtendidas(String idMedico) throws Exception {
        Medico medico = medicoRepositorio.findByCedula(idMedico);
        if (medico==null){
            throw new Exception("No existe un medico con el codigo "+idMedico);
        }

        List<Cita> citasAtendidas = citaRepo.findByMedicoAndEstadoCita(medico, EstadoCita.terminado);
        List<HistorialPacientesAtendidosDto> historialPacientesAtendidosDtos= new ArrayList<>();

        for (Cita c: citasAtendidas){
            Consulta consulta = c.getConsulta();
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

        List<Cita> citasEnDia = citaRepo.findByMedicoAndFechaCita(medico, diaLibreDto.fecha());
        if (!citasEnDia.isEmpty()) {
            throw new Exception("El médico tiene citas programadas en el día seleccionado. No se puede asignar un día libre.");
        }

        DiaLibre diaLibre = new DiaLibre();
        diaLibre.setDia(diaLibreDto.fecha());
        diaLibre.setMedico(medico);

        // Guardar el día libre en la entidad DiaLibre
        diaLibreRepositorio.save(diaLibre);
        return diaLibre.getId();
    }

    @Override
    public List<DiaLibreDto> listaDiasLibresMedico(String idMedico) throws Exception {

        Medico medico = medicoRepositorio.findByCedula(idMedico);
        if (medico== null){
            throw new Exception("No existe un medico con el codigo "+ idMedico);
        }
        List<DiaLibre> diaLibres= diaLibreRepositorio.findByMedicoAndDiaGreaterThanEqual(medico, LocalDateTime.now());

        List<DiaLibreDto> resultado= diaLibres.stream().map(
                diaLibre -> new DiaLibreDto(
                        diaLibre.getDia(),
                        diaLibre.getMedico().getCedula()
                )).toList();

        return resultado;
    }


}
