package co.edu.uniquindio.proyectoclinica.model.services;

import co.edu.uniquindio.proyectoclinica.model.dto.CitaMedicoDTo;
import co.edu.uniquindio.proyectoclinica.model.dto.CitasMedicoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.DetalleCitaDto;
import co.edu.uniquindio.proyectoclinica.model.dto.HistorialPacientesAtendidosDto;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoService {

    List<CitaMedicoDTo> verCitasPendientes(String codigoMedico);
    DetalleCitaDto obtenerCita(String id);

    void atencerCita();


    List<HistorialPacientesAtendidosDto> listarCitasAtendidas ();

    void listarCitasPaciente(); //Historial medico

    void agendarDiaLibre();

    List<CitasMedicoDto> listarCitasMedico();


}
